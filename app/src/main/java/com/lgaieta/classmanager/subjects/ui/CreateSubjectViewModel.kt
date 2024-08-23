package com.lgaieta.classmanager.subjects.ui

import OfflineSubjectRepository
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CreateSubjectViewModel(
    private val offlineSubjectRepository: OfflineSubjectRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(CreateSubjectState())
    val uiState: StateFlow<CreateSubjectState> = _uiState.asStateFlow()

    fun changeName(newName: String) {
        _uiState.update {
            it.copy(name = newName)
        }
    }
    private fun validateName() {
        return with(_uiState.value) {
            name.isNotBlank()
        }
    }

    suspend fun submitSubject() {
    }
}

data class CreateSubjectState(
    val name: String = ""
)