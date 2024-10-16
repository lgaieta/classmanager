package com.lgaieta.classmanager.tasks.ui

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
            val task1 = Task(id = 1, name = "Trabajo práctico de lenguaje",description = "", subjectId = 2)
            val task2 = Task(id = 2, name = "Ensayo sobre teorías del desarrollo cognitivo",description = "", subjectId = 6)
            val task3 = Task(id = 3, name = "Investigación de causas de la revolución industrial", description = "", subjectId = 8)
            val task4 = Task(id = 4, name = "Proyecto sobre el ciclo del agua", description = "", subjectId = 9)
            val task5 = Task(id = 5, name = "Examen de álgebra lineal", description = "", subjectId = 1)
            val task6 = Task(id = 6, name = "Análisis de comportamiento en grupos sociales", description = "", subjectId = 8)
            val task7 = Task(id = 7, name = "Tarea sobre fotosíntesis en plantas", description = "", subjectId = 9)
            val task8 = Task(id = 8, name = "Informe sobre la teoría del apego", description = "", subjectId = 6)
            val task9 = Task(id = 9, name = "Estudio de movimientos tectónicos", description = "", subjectId = 1)
            val task10 = Task(id = 10, name = "Resolución de problemas de geometría euclidiana", description = "", subjectId = 1)

            offlineTaskRepository.insert(task1)
            offlineTaskRepository.insert(task2)
            offlineTaskRepository.insert(task3)
            offlineTaskRepository.insert(task4)
            offlineTaskRepository.insert(task5)
            offlineTaskRepository.insert(task6)
            offlineTaskRepository.insert(task7)
            offlineTaskRepository.insert(task8)
            offlineTaskRepository.insert(task9)
            offlineTaskRepository.insert(task10)

        }
    }
}