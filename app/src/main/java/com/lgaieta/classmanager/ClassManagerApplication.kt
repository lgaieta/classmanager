package com.lgaieta.classmanager

import android.app.Application
import com.lgaieta.classmanager.subjects.ui.DefaultSubjectModelsContainer
import com.lgaieta.classmanager.subjects.ui.SubjectModelsContainer
import com.lgaieta.classmanager.tasks.ui.DefaultTaskModelsContainer
import com.lgaieta.classmanager.tasks.ui.TaskModelsContainer

class ClassManagerApplication : Application() {
    companion object {
        lateinit var subjectModelsContainer: SubjectModelsContainer
        lateinit var taskModelsContainer: TaskModelsContainer

    }

    override fun onCreate() {
        super.onCreate()
        subjectModelsContainer = DefaultSubjectModelsContainer(this)
        taskModelsContainer = DefaultTaskModelsContainer(this)
    }
}