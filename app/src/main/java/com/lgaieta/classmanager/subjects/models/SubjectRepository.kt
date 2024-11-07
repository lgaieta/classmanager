package com.lgaieta.classmanager.subjects.models

import com.lgaieta.classmanager.students.models.Student
import com.lgaieta.classmanager.tasks.models.Task
import kotlinx.coroutines.flow.Flow

interface SubjectRepository {
    fun getAllSubjectsStream(): Flow<List<Subject>>
    fun getSubjectStream(id: Int): Flow<Subject?>
    fun getTasksStream(subjectId: Int): Flow<List<Task>>
    fun getStudentsStream(subjectId: Int): Flow<List<Student>>
    suspend fun insert(subject: Subject)
    suspend fun delete(subject: Subject)
    suspend fun update(subject: Subject)
}