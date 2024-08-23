package com.lgaieta.classmanager.subjects.models

import kotlinx.coroutines.flow.Flow

interface SubjectRepository {
    fun getAllSubjectsStream(): Flow<List<Subject>>
    fun getSubjectStream(id: Int): Flow<Subject?>
    suspend fun insert(subject: Subject)
    suspend fun delete(subject: Subject)
    suspend fun update(subject: Subject)
}