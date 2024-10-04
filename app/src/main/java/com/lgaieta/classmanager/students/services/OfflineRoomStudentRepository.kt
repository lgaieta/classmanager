package com.lgaieta.classmanager.students.services

import com.lgaieta.classmanager.students.models.StudentRepository
import com.lgaieta.classmanager.students.models.Student
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

    override fun getStudentStream(id: Int): Flow<Student?> =
        studentDao.getStudent(id).map { it?.toStudent() }

    override fun getSubjectsStream(studentId: Int): Flow<List<Subject>> =
        studentDao.getSubjects(studentId)

    override suspend fun insert(student: Student) =
        studentDao.insert(StudentRoomEntity.fromStudent(student))

    override suspend fun delete(student: Student) =
        studentDao.delete(StudentRoomEntity.fromStudent(student))

    override suspend fun update(student: Student) =
        studentDao.update(StudentRoomEntity.fromStudent(student))

    override suspend fun assignSubject(studentId: Int, subjectId: Int) =
        studentDao.assignSubject(studentId, subjectId)
}