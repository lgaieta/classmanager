package com.lgaieta.classmanager.tasks.services

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lgaieta.classmanager.students.services.StudentRoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentRoomDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: StudentRoomEntity)

    @Update
    suspend fun update(task: StudentRoomEntity)

    @Delete
    suspend fun delete(task: StudentRoomEntity)

    @Query("INSERT INTO subject_student (subjectId, studentId) VALUES (:subjectId, :studentId)")
    suspend fun assignSubject(studentId: Int, subjectId: Int)

    @Query("SELECT * from student WHERE id = :id")
    fun getStudent(id: Int): Flow<StudentRoomEntity?>

    @Query("SELECT * from student ORDER BY name ASC")
    fun getAllStudents(): Flow<List<StudentRoomEntity>>
}