package com.lgaieta.classmanager.subjects.ui.add_students

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lgaieta.classmanager.students.models.StudentRepository
import com.lgaieta.classmanager.students.ui.list.StudentsListState
import com.lgaieta.classmanager.subjects.models.SubjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddStudentsViewModel(
    private val offlineStudentRepository: StudentRepository,
    private val afterStudentClick: (id: Long) -> Unit,
    private val afterSubmit: () -> Unit,
    private val subjectId: Int,
) : ViewModel() {
    val studentsListState: StateFlow<StudentsListState> =
        offlineStudentRepository.getAllStudentsStream().map { StudentsListState(students = it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = StudentsListState()
            )

    private val _selectedStudentsIds = MutableStateFlow<Set<Long>>(emptySet())
    val selectedStudentsIds = _selectedStudentsIds.asStateFlow()

    fun onStudentClick(id: Long) {
        _selectedStudentsIds.update {
            if (it.contains(id)) {
                it - id
            } else {
                it + id
            }
        }
        afterStudentClick(id)
    }

    suspend fun onSubmit() {
        viewModelScope.launch {
            val studentSubjectPairs = selectedStudentsIds.value.map { studentId -> Pair(studentId, subjectId) }
            offlineStudentRepository.assignSubjects(studentSubjectPairs)
            afterSubmit()
        }
    }
}