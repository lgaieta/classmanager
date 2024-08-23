package com.lgaieta.classmanager.subjects.ui

import OfflineSubjectRepository
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DeleteSubjectViewModel(
    private val offlineSubjectRepository: OfflineSubjectRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(DeleteSubjectState())
    val uiState: StateFlow<DeleteSubjectState> = _uiState.asStateFlow()

    fun deleteSubject(id: Int) {
        //_uiState.{
        //}
    }

}

data class DeleteSubjectState(
    val id: Int = 0
)