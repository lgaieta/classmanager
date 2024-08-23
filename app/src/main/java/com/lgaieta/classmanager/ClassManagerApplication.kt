package com.lgaieta.classmanager

import OfflineSubjectRepository
import android.app.Application
import android.content.Context
import com.lgaieta.classmanager.subjects.models.SubjectRepository
import com.lgaieta.classmanager.subjects.services.OfflineDatabase

class DefaultAppContainer(private val context: Context) : AppContainer {
    override val offlineSubjectRepository: SubjectRepository by lazy {
        OfflineSubjectRepository(
            OfflineDatabase.getDatabase(context).subjectDao()
        )
    }
}

class ClassManagerApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}