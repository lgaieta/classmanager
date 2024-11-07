package com.lgaieta.classmanager.subjects.services

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lgaieta.classmanager.students.models.Student
import com.lgaieta.classmanager.students.services.StudentRoomEntity
import com.lgaieta.classmanager.subjects.models.Subject
import com.lgaieta.classmanager.tasks.services.TaskRoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectRoomDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(subject: SubjectRoomEntity)

    @Update
    suspend fun update(subject: SubjectRoomEntity)

    @Delete
    suspend fun delete(subject: SubjectRoomEntity)

    @Query("SELECT * from subject WHERE id = :id")
    fun getSubject(id: Int): Flow<SubjectRoomEntity?>

    @Query("SELECT * from subject ORDER BY name ASC")
    fun getAllSubjects(): Flow<List<SubjectRoomEntity>>

    @Query("SELECT * FROM task WHERE subjectId = :subjectId")
    fun getTasks(subjectId: Int): Flow<List<TaskRoomEntity>>

    @Query(
        """
        SELECT student.* 
        FROM subject_student 
        JOIN student ON subject_student.studentId = student.id 
        WHERE subject_student.subjectId = :subjectId
        """
    )
    fun getStudents(subjectId: Int): Flow<List<Student>>



}