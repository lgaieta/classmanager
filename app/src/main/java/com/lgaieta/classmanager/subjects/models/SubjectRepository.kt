package com.lgaieta.classmanager.subjects.models

interface SubjectRepository {
    fun create(subject: Subject): Unit
    fun getList(): List<Subject>
}