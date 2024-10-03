package com.lgaieta.classmanager.tasks.services

import com.lgaieta.classmanager.tasks.services.TaskRoomEntity


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskRoomDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: TaskRoomEntity)

    @Update
    suspend fun update(task: TaskRoomEntity)

    @Delete
    suspend fun delete(task: TaskRoomEntity)

    @Query("SELECT * from task WHERE id = :id")
    fun getTask(id: Int): Flow<TaskRoomEntity?>

    @Query("SELECT * from task ORDER BY name ASC")
    fun getAllTasks(): Flow<List<TaskRoomEntity>>

}