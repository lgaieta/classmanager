package com.lgaieta.classmanager.students.models

import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    fun getAllStudentsStream(): Flow<List<Student>>
    fun getStudentStream(id: Int): Flow<Student?>
    suspend fun insert(subject: Student)
    suspend fun delete(subject: Student)
    suspend fun update(subject: Student)
}