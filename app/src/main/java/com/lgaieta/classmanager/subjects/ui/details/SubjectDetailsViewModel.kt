package com.lgaieta.classmanager.subjects.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lgaieta.classmanager.subjects.models.Subject
import com.lgaieta.classmanager.subjects.models.SubjectRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.stateIn

class SubjectDetailsViewModel(
    private val offlineSubjectRepository: SubjectRepository,
    private val subjectId: Int,
) : ViewModel() {
    val subjectDetailsState: StateFlow<SubjectDetailsState> =
        offlineSubjectRepository.getSubjectStream(subjectId)
            .filterNotNull()
            .map { subject -> SubjectDetailsState(subject) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = SubjectDetailsState()
            )
}

data class SubjectDetailsState(
    val subject: Subject? = null,
)