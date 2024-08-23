package com.lgaieta.classmanager.subjects.ui

import OfflineSubjectRepository
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailedSubjectViewModel(
    private val offlineSubjectRepository: OfflineSubjectRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(DetailedSubjectState())
    val uiState: StateFlow<DetailedSubjectState> = _uiState.asStateFlow()

    fun showData(newName: String) {
        //_uiState {
          //
       // }
    }
}

data class DetailedSubjectState(
    val course: String = "",
    val time: Int = 0,
    val task:String = "",
    val student:String = ""
)