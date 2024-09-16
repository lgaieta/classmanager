package com.lgaieta.classmanager.tasks.ui


import OfflineRoomSubjectRepository
import OfflineRoomTaskRepository
import android.content.Context
import com.lgaieta.classmanager.tasks.models.Task
import com.lgaieta.classmanager.tasks.models.TaskRepository
import com.lgaieta.classmanager.subjects.services.OfflineRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface TaskModelsContainer {
    val offlineTaskRepository: TaskRepository
}

class DefaultTaskModelsContainer(private val context: Context) : TaskModelsContainer {
    override val offlineTaskRepository: TaskRepository by lazy {
        OfflineRoomTaskRepository(
            OfflineRoomDatabase.getDatabase(context).taskDao()
        )
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val task1 = Task(id = 1, name = "Matemáticas")
            val task2 = Task(id = 2, name = "Ciencias")


            offlineTaskRepository.insert(task1)
            offlineTaskRepository.insert(task2)
        }
    }
}