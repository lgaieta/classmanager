package com.lgaieta.classmanager.students.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lgaieta.classmanager.students.models.Student
import com.lgaieta.classmanager.students.models.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditStudentViewModel(
    private val offlineStudentRepository: StudentRepository,
    studentId: Long,
    private val afterEdit: (id: Long) -> Unit = {},
) : ViewModel() {
    private val _editStudentState = MutableStateFlow(EditStudentState())

    val editStudentState: StateFlow<EditStudentState> = _editStudentState.asStateFlow()

    init {
        viewModelScope.launch {
            offlineStudentRepository.getStudentStream(studentId)
                .collect { student ->
                    _editStudentState.value = EditStudentState(
                        name = student?.name ?: "",
                        storedStudent = student
                    )
                }
        }
    }

    fun changeName(newName: String) {
        _editStudentState.update {
            it.copy(name = newName)
        }
    }

    suspend fun editStudent() {
        val currentState = _editStudentState.value
        val updatedStudent = currentState.storedStudent?.copy(
            name = currentState.name,
        )
        if (updatedStudent != null) {
            offlineStudentRepository.update(updatedStudent)
            afterEdit(updatedStudent.id)
        }
    }
}

data class EditStudentState(
    val name: String = "",
    val storedStudent: Student? = null,
)