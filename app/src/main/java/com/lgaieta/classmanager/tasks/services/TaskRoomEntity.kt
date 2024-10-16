package com.lgaieta.classmanager.tasks.services

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lgaieta.classmanager.tasks.models.Task


@Entity(tableName = "task")
data class TaskRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val subjectId: Int,
) {
    fun toTask() = Task(id, name, description, subjectId)

    companion object {
        fun fromTask(task: Task) = TaskRoomEntity(task.id, task.name, task.description, task.subjectId)
    }
}