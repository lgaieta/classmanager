package com.lgaieta.classmanager.tasks.models

import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllTasksStream(): Flow<List<Task>>
    fun getTaskStream(id: Int): Flow<Task?>
    suspend fun insert(task: Task)
    suspend fun delete(task: Task)
    suspend fun update(task: Task)
}