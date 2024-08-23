package com.lgaieta.classmanager.subjects.ui


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EditSubjectViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(EditSubjectState())
    val uiState: StateFlow<EditSubjectState> = _uiState.asStateFlow()

    fun changeName(newName: String) {
        _uiState.update {
            it.copy(name = newName)
        }
    }
    fun changeCourse(newCourse: String) {
        _uiState.update {
            it.copy(course = newCourse)
        }
    }
    fun changeTime(newTime: Int) {
        _uiState.update {
            it.copy(time = newTime)
        }
    }

    suspend fun submitEditSubject() {

    }
}

data class EditSubjectState (
    val name: String = "",
    val course: String = "",
    val time: Int = 0,
)