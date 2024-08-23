package com.lgaieta.classmanager.subjects.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SubjectViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(SubjectState())
    val uiState: StateFlow<SubjectState> = _uiState.asStateFlow()

    fun showDataSubjects(){

    }

}

data class SubjectState (
    val name: String = "",
    val details: String = "",
    val time: Int = 0,

)
// Prueba
