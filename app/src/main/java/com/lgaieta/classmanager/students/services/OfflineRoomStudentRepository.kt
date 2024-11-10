package com.lgaieta.classmanager.students.services

import com.lgaieta.classmanager.students.models.StudentRepository
import com.lgaieta.classmanager.students.models.Student
import com.lgaieta.classmanager.students.models.StudentWithNote
import com.lgaieta.classmanager.subjects.models.Subject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineRoomStudentRepository(private val studentDao: StudentRoomDao) : StudentRepository {
    override fun getAllStudentsStream(): Flow<List<Student>> =
        studentDao.getAllStudents().map { list ->
            list.map {
                it.toStudent()
            }
        }

    override fun getStudentStream(id: Long): Flow<Student?> =
        studentDao.getStudent(id).map { it?.toStudent() }

    override fun getStudentsStream(subjectId: Int): Flow<List<Student>> =
        studentDao.getStudents(subjectId).map { list -> list.map { it.toStudent() } }

    override fun getSubjectsStream(studentId: Long): Flow<List<Subject>> =
        studentDao.getSubjects(studentId)

    override fun getAllStudentsInSubject(subjectId: Int): Flow<List<Student>> =
        studentDao.getAllStudentsInSubject(subjectId).map { list ->
            list.map {
                it.toStudent()
            }
        }

    override fun getByTask(taskId: Int): Flow<List<StudentWithNote>> =
        studentDao.getByTask(taskId).map { list ->
            list.map {
                it.toStudentWithNote()
            }
        }

    override suspend fun insert(student: Student) =
        studentDao.insert(StudentRoomEntity.fromStudent(student))

    override suspend fun delete(student: Student) =
        studentDao.delete(StudentRoomEntity.fromStudent(student))

    override suspend fun update(student: Student) =
        studentDao.update(StudentRoomEntity.fromStudent(student))

    override suspend fun assignSubject(studentId: Long, subjectId: Int) =
        studentDao.assignSubject(studentId, subjectId)

    override suspend fun assignSubjects(studentSubjectPairs: List<Pair<Long, Int>>) =
        studentDao.assignSubjects(studentSubjectPairs.map {
            SubjectStudentCrossRef(
                studentId = it.first,
                subjectId = it.second
            )
        })

    override suspend fun removeSubject(studentId: Long, subjectId: Int) =
        studentDao.removeSubject(studentId = studentId, subjectId = subjectId)

    override suspend fun removeSubjects(studentSubjectPairs: List<Pair<Long, Int>>) =
        studentDao.removeSubjects(studentSubjectPairs.map {
            SubjectStudentCrossRef(
                studentId = it.first,
                subjectId = it.second
            )
        })

    override suspend fun updateNote(studentWithNote: StudentWithNote, taskId: Int) =
        studentDao.updateNote(
            TaskStudentCrossRef(
                studentId = studentWithNote.student.id,
                taskId = taskId,
                note = studentWithNote.note ?: 0f
            )
        )
}