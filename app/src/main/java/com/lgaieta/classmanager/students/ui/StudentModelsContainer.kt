package com.lgaieta.classmanager.students.ui

import android.content.Context
import com.lgaieta.classmanager.services.OfflineRoomDatabase
import com.lgaieta.classmanager.students.models.StudentRepository
import com.lgaieta.classmanager.students.services.OfflineRoomStudentRepository

interface StudentModelsContainer {
    val offlineStudentRepository: StudentRepository
}

class DefaultStudentModelsContainer(private val context: Context) : StudentModelsContainer {
    override val offlineStudentRepository: StudentRepository by lazy {
        OfflineRoomStudentRepository(
            OfflineRoomDatabase.getDatabase(context).studentDao()
        )
    }
}