package com.lgaieta.classmanager.tasks.ui


import OfflineRoomSubjectRepository
import OfflineRoomTaskRepository
import android.content.Context
import com.lgaieta.classmanager.tasks.models.Task
import com.lgaieta.classmanager.tasks.models.TaskRepository
import com.lgaieta.classmanager.services.OfflineRoomDatabase
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
            val task1 = Task(id = 1, name = "Trabajo práctico de sistemas digitales", subjectId = 1)
            val task2 = Task(id = 2, name = "Tarea de matemática II", subjectId = 1)
            val task3 = Task(id = 3, name = "Tarea de ciencias sociales", subjectId = 1)
            val task4 = Task(id = 4, name = "Tarea de organigramas", subjectId = 1)
            val task5 = Task(id = 5, name = "Tarea evaluativa de ESI", subjectId = 1)



            offlineTaskRepository.insert(task1)
            offlineTaskRepository.insert(task2)
            offlineTaskRepository.insert(task3)
            offlineTaskRepository.insert(task4)
            offlineTaskRepository.insert(task5)
        }
    }
}