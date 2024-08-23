package com.lgaieta.classmanager.subjects.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SubjectViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(SubjectState())
    val uiState: StateFlow<SubjectState> = _uiState.asStateFlow()

    fun changeName(newName: String) {
        _uiState.update {
            it.copy(name = newName)
        }
    }
    fun changeTime(newTime: Int) {
        _uiState.update {
            it.copy(time = newTime)
        }
    }
    suspend fun submitSubject() {

    }
}

data class SubjectState (
    val name: String = "",
    val time: Int = 0
)
