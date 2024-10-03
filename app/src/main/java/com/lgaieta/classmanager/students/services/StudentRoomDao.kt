package com.lgaieta.classmanager.students.services

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lgaieta.classmanager.subjects.models.Subject
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentRoomDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: StudentRoomEntity)

    @Update
    suspend fun update(task: StudentRoomEntity)

    @Delete
    suspend fun delete(task: StudentRoomEntity)

    @Query("INSERT OR IGNORE INTO subject_student (subjectId, studentId) VALUES (:subjectId, :studentId)")
    suspend fun assignSubject(studentId: Int, subjectId: Int)

    @Query("SELECT * from student WHERE id = :id")
    fun getStudent(id: Int): Flow<StudentRoomEntity?>

    @Query("""
        SELECT subject.* 
        FROM subject_student 
        JOIN subject ON subject_student.subjectId = subject.id 
        WHERE subject_student.studentId = :studentId
    """)
    fun getSubjects(studentId: Int): Flow<List<Subject>>


    @Query("SELECT * from student ORDER BY name ASC")
    fun getAllStudents(): Flow<List<StudentRoomEntity>>
}