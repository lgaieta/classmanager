package com.lgaieta.classmanager.subjects.services

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lgaieta.classmanager.subjects.models.Subject
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectRoomDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(subject: Subject)

    @Update
    suspend fun update(subject: Subject)

    @Delete
    suspend fun delete(subject: Subject)

    @Query("SELECT * from subject WHERE id = :id")
    fun getSubject(id: Int): Flow<Subject>

    @Query("SELECT * from subject ORDER BY name ASC")
    fun getAllSubjects(): Flow<List<Subject>>

}