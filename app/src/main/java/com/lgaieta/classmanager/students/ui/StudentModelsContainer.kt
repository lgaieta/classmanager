package com.lgaieta.classmanager.students.ui

import android.content.Context
import com.lgaieta.classmanager.services.OfflineRoomDatabase
import com.lgaieta.classmanager.students.models.Student
import com.lgaieta.classmanager.students.models.StudentRepository
import com.lgaieta.classmanager.students.services.OfflineRoomStudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface StudentModelsContainer {
    val offlineStudentRepository: StudentRepository
}

class DefaultStudentModelsContainer(private val context: Context) : StudentModelsContainer {
    override val offlineStudentRepository: StudentRepository by lazy {
        OfflineRoomStudentRepository(
            OfflineRoomDatabase.getDatabase(context).studentDao()
        )
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val subject1 = Student(id = 1, name = "Aieta Luciano")
            val subject2 = Student(id = 2, name = "Tello Elias")

            offlineStudentRepository.insert(subject1)
            offlineStudentRepository.insert(subject2)
            offlineStudentRepository.assignSubject(subject1.id, 2)
            offlineStudentRepository.assignSubject(subject2.id, 4)
            offlineStudentRepository.assignSubject(subject2.id, 5)
        }
    }
}