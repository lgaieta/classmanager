package com.lgaieta.classmanager.students.models

data class Student(
    val id: Long,
    val name: String,
)

data class StudentWithNote(
    val student: Student,
    val note: Float?
)