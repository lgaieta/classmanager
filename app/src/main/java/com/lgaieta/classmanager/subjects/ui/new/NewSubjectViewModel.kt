package com.lgaieta.classmanager.subjects.ui.new

import androidx.lifecycle.ViewModel
import com.lgaieta.classmanager.subjects.models.Subject
import com.lgaieta.classmanager.subjects.models.SubjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewSubjectViewModel(
    private val offlineSubjectRepository: SubjectRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewSubjectState())
    val uiState: StateFlow<NewSubjectState> = _uiState.asStateFlow()

    fun changeName(newName: String) {
        _uiState.update {
            it.copy(name = newName)
        }
    }

    private fun isNameValid(): Boolean {
        return _uiState.value.name.isNotBlank()
    }

    suspend fun saveSubject() {
        if (isNameValid()) {
            offlineSubjectRepository.insert(uiState.value.toSubject())
            _uiState.update {
                it.copy(name = "")
            }
        }
    }
}

data class NewSubjectState(
    val name: String = ""
) {
    fun toSubject() = Subject(id = 0, name = name)
}