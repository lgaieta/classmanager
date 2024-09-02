package com.lgaieta.classmanager

import android.app.Application
import com.lgaieta.classmanager.subjects.ui.DefaultSubjectModelsContainer
import com.lgaieta.classmanager.subjects.ui.SubjectModelsContainer

class ClassManagerApplication : Application() {
    companion object {
        lateinit var subjectModelsContainer: SubjectModelsContainer
    }

    override fun onCreate() {
        super.onCreate()
        subjectModelsContainer = DefaultSubjectModelsContainer(this)
    }
}