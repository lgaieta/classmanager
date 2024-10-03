package com.lgaieta.classmanager.students.models

import com.lgaieta.classmanager.subjects.models.Subject
import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    fun getAllStudentsStream(): Flow<List<Student>>
    fun getStudentStream(id: Int): Flow<Student?>
    fun getSubjectsStream(studentId: Int): Flow<List<Subject>>
    suspend fun insert(student: Student)
    suspend fun delete(student: Student)
    suspend fun update(student: Student)
    suspend fun assignSubject(studentId: Int, subjectId: Int)
}