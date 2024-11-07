package com.lgaieta.classmanager.subjects.ui

import OfflineRoomSubjectRepository
import android.content.Context
import com.lgaieta.classmanager.subjects.models.Subject
import com.lgaieta.classmanager.subjects.models.SubjectRepository
import com.lgaieta.classmanager.services.OfflineRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface SubjectModelsContainer {
    val offlineSubjectRepository: SubjectRepository
}

class DefaultSubjectModelsContainer(private val context: Context) : SubjectModelsContainer {
    override val offlineSubjectRepository: SubjectRepository by lazy {
        OfflineRoomSubjectRepository(
            OfflineRoomDatabase.getDatabase(context).subjectDao()
        )
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val subject1 = Subject(id = 1, name = "Matemáticas", info = "7mo 2da")
            val subject2 = Subject(id = 2, name = "Ciencias", info = "5to 3ra")
            val subject3 = Subject(id = 3, name = "Historia", info = "6to 1ra")
            val subject4 = Subject(id = 4, name = "Lengua Española", info = "4to 2da")
            val subject5 = Subject(id = 5, name = "Biología", info = "5to 2da")
            val subject6 = Subject(id = 6, name = "Geografía", info = "7mo 3ra")
            val subject7 = Subject(id = 7, name = "Educación Física", info = "3ro 1ra")
            val subject8 = Subject(id = 8, name = "Filosofía", info = "6to 2da")
            val subject9 = Subject(id = 9, name = "Arte", info = "4to 3ra")
            val subject10 = Subject(id = 10, name = "Informática", info = "7mo 1ra")

            offlineSubjectRepository.insert(subject1)
            offlineSubjectRepository.insert(subject2)
            offlineSubjectRepository.insert(subject3)
            offlineSubjectRepository.insert(subject4)
            offlineSubjectRepository.insert(subject5)
            offlineSubjectRepository.insert(subject6)
            offlineSubjectRepository.insert(subject7)
            offlineSubjectRepository.insert(subject8)
            offlineSubjectRepository.insert(subject9)
            offlineSubjectRepository.insert(subject10)
        }
    }
}