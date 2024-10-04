package com.lgaieta.classmanager.students.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lgaieta.classmanager.students.models.Student
import com.lgaieta.classmanager.students.models.StudentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class StudentsListViewModel(offlineStudentRepository: StudentRepository) : ViewModel() {
    val studentsListState: StateFlow<StudentsListState> =
        offlineStudentRepository.getAllStudentsStream().map { StudentsListState(students = it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = StudentsListState()
            )
}

data class StudentsListState(
    val students: List<Student> = emptyList()
)
