package com.lgaieta.classmanager.subjects.ui
import OfflineRoomSubjectRepository
import android.content.Context
import com.lgaieta.classmanager.subjects.models.SubjectRepository
import com.lgaieta.classmanager.subjects.services.OfflineRoomDatabase

interface SubjectModelsContainer {
    val offlineSubjectRepository: SubjectRepository
}

class DefaultSubjectModelsContainer(private val context: Context) : SubjectModelsContainer {
    override val offlineSubjectRepository: SubjectRepository by lazy {
        OfflineRoomSubjectRepository(
            OfflineRoomDatabase.getDatabase(context).subjectDao()
        )
    }
}