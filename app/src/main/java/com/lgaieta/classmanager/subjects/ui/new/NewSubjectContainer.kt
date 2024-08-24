package com.lgaieta.classmanager.subjects.ui.new

import OfflineRoomSubjectRepository
import android.content.Context
import com.lgaieta.classmanager.subjects.models.SubjectRepository
import com.lgaieta.classmanager.subjects.services.OfflineRoomDatabase

interface NewSubjectContainer {
    val offlineSubjectRepository: SubjectRepository
}

class DefaultNewSubjectContainer(private val context: Context) : NewSubjectContainer {
    override val offlineSubjectRepository: SubjectRepository by lazy {
        OfflineRoomSubjectRepository(
            OfflineRoomDatabase.getDatabase(context).subjectDao()
        )
    }
}