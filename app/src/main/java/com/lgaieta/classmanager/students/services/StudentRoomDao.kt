package com.lgaieta.classmanager.students.services

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.lgaieta.classmanager.students.models.Student
import com.lgaieta.classmanager.students.models.StudentWithNote
import com.lgaieta.classmanager.subjects.models.Subject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

@Dao
interface StudentRoomDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: StudentRoomEntity): Long

    @Update
    suspend fun update(task: StudentRoomEntity)

    @Delete
    suspend fun delete(task: StudentRoomEntity)

    @Query("SELECT * from student WHERE id = :id")
    fun getStudent(id: Long): Flow<StudentRoomEntity?>

    @Query(
        """
        SELECT student.* 
        FROM subject_student 
        JOIN student ON subject_student.studentId = student.id 
        WHERE subject_student.subjectId = :subjectId
        """
    )
    fun getStudents(subjectId: Int): Flow<List<StudentRoomEntity>>

    @Query(
        """
        SELECT subject.* 
        FROM subject_student 
        JOIN subject ON subject_student.subjectId = subject.id 
        WHERE subject_student.studentId = :studentId
        """
    )
    fun getSubjects(studentId: Long): Flow<List<Subject>>

    @Query(
        """
        SELECT student.*, task_student.note
        FROM student
        JOIN subject_student ON student.id = subject_student.studentId
        LEFT JOIN task_student ON student.id = task_student.studentId AND task_student.taskId = :taskId
        LEFT JOIN task ON task.id = :taskId AND task.subjectId = subject_student.subjectId
        WHERE task.id IS NOT NULL
        """
    )
    fun getByTask(taskId: Int): Flow<List<StudentRoomEntityWithNote>>

    @Query("SELECT * from student ORDER BY name ASC")
    fun getAllStudents(): Flow<List<StudentRoomEntity>>

    @Query(
        """
        SELECT student.* 
        FROM student 
        JOIN subject_student ON student.id = subject_student.studentId 
        WHERE subject_student.subjectId = :subjectId
        """
    )
    fun getAllStudentsInSubject(subjectId: Int): Flow<List<StudentRoomEntity>>

    @Query("INSERT OR IGNORE INTO subject_student (studentId, subjectId) VALUES (:studentId, :subjectId)")
    suspend fun assignSubject(studentId: Long, subjectId: Int)

    @Transaction
    suspend fun assignSubjects(crossRefs: List<SubjectStudentCrossRef>) {
        crossRefs.forEach { pair ->
            assignSubject(pair.studentId, pair.subjectId)
        }
    }

    @Query("DELETE FROM subject_student WHERE studentId = :studentId AND subjectId = :subjectId")
    suspend fun removeSubject(studentId: Long, subjectId: Int)

    @Transaction
    suspend fun removeSubjects(crossRefs: List<SubjectStudentCrossRef>) {
        crossRefs.forEach { pair ->
            removeSubject(pair.studentId, pair.subjectId)
        }
    }
}