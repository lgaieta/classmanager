package com.lgaieta.classmanager.students.ui.new

import androidx.lifecycle.ViewModel
import com.lgaieta.classmanager.students.models.Student
import com.lgaieta.classmanager.students.models.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewStudentViewModel(
    private val offlineStudentRepository: StudentRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewStudentState())
    val uiState: StateFlow<NewStudentState> = _uiState.asStateFlow()

    fun changeName(newName: String) {
        _uiState.update {
            it.copy(name = newName)
        }
    }

    private fun isNameValid(): Boolean {
        return _uiState.value.name.isNotBlank()
    }

    suspend fun saveStudent() {
        if (isNameValid()) {
            offlineStudentRepository.insert(uiState.value.toStudent())
            _uiState.update {
                it.copy(name = "")
            }
        }
    }
}

data class NewStudentState(
    val name: String = "",
) {
    fun toStudent() = Student(id = 0, name = name)
}