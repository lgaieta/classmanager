package com.lgaieta.classmanager.subjects.ui.new

import androidx.lifecycle.ViewModel
import com.lgaieta.classmanager.subjects.models.Subject
import com.lgaieta.classmanager.subjects.models.SubjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewSubjectViewModel(
    private val offlineSubjectRepository: SubjectRepository,
    private val afterCancel: () -> Unit,
    private val afterSave: () -> Unit,
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewSubjectState())
    val uiState: StateFlow<NewSubjectState> = _uiState.asStateFlow()

    fun changeName(newName: String) {
        _uiState.update {
            it.copy(name = newName)
        }
    }

    fun changeInfo(newInfo: String) {
        _uiState.update {
            it.copy(info = newInfo)
        }
    }

    fun cancel() {
        _uiState.update {
            it.copy(name = "", info = "")
        }
        afterCancel()
    }

    private fun isNameValid(): Boolean {
        return _uiState.value.name.isNotBlank()
    }

    private fun isInfoValid(): Boolean {
        return _uiState.value.info.isNotBlank()
    }

    suspend fun saveSubject() {
        if (isNameValid()) {
            offlineSubjectRepository.insert(uiState.value.toSubject())
            _uiState.update {
                it.copy(name = "", info = "")
            }
            afterSave()
        }else{
            _uiState.update { it.copy(nameError = true) }
        }
    }
}

data class NewSubjectState(
    val name: String = "",
    val info: String = "",
    val nameError: Boolean = false
) {
    fun toSubject() = Subject(id = 0, name = name, info = info)
}