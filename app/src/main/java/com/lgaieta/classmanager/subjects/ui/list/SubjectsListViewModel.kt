package com.lgaieta.classmanager.subjects.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lgaieta.classmanager.subjects.models.Subject
import com.lgaieta.classmanager.subjects.models.SubjectRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SubjectsListViewModel(offlineSubjectRepository: SubjectRepository) : ViewModel() {
    val subjectListState: StateFlow<SubjectListState> =
        offlineSubjectRepository.getAllSubjectsStream().map { SubjectListState(subjects = it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = SubjectListState()
            )
}

data class SubjectListState(
    val subjects: List<Subject> = emptyList()
)
