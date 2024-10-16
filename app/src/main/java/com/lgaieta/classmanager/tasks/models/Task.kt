package com.lgaieta.classmanager.tasks.models

data class Task (
    val id: Int,
    val name: String,
    val description: String,
    val subjectId: Int
)