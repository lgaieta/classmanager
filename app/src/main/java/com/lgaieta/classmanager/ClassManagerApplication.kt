package com.lgaieta.classmanager

import android.app.Application
import com.lgaieta.classmanager.subjects.ui.new.DefaultNewSubjectContainer
import com.lgaieta.classmanager.subjects.ui.new.NewSubjectContainer

class ClassManagerApplication : Application() {
    companion object {
        lateinit var newSubjectContainer: NewSubjectContainer
    }

    override fun onCreate() {
        super.onCreate()
        newSubjectContainer = DefaultNewSubjectContainer(this)
    }
}