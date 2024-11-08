package com.lgaieta.classmanager.students.models

import com.lgaieta.classmanager.subjects.models.Subject
import kotlinx.coroutines.flow.Flow

interface StudentRepository {
    fun getAllStudentsStream(): Flow<List<Student>>
    fun getStudentStream(id: Long): Flow<Student?>
    fun getStudentsStream(subjectId: Int): Flow<List<Student>>
    fun getStudentsTaskStream(subjectId: Int) : Flow<List<Student>>
    fun getSubjectsStream(studentId: Long): Flow<List<Subject>>
    suspend fun insert(student: Student): Long
    suspend fun delete(student: Student)
    suspend fun update(student: Student)
    suspend fun assignSubject(studentId: Long, subjectId: Int)
    suspend fun assignSubjects(studentSubjectPairs: List<Pair<Long, Int>>)
    suspend fun removeSubject(studentId: Long, subjectId: Int)
    suspend fun removeSubjects(studentSubjectPairs: List<Pair<Long, Int>>)
}