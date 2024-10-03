package com.lgaieta.classmanager.subjects.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lgaieta.classmanager.subjects.models.Subject
import com.lgaieta.classmanager.subjects.models.SubjectRepository
import com.lgaieta.classmanager.tasks.models.Task
import com.lgaieta.classmanager.ui.navigation.ClassManagerScreen
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SubjectDetailsViewModel(
    private val offlineSubjectRepository: SubjectRepository,
    subjectId: Int,
    private val afterEdit: (id: Int) -> Unit = {},
    private val afterDelete: (id: Int) -> Unit = {}
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

    val tasksState: StateFlow<List<Task>> =
        offlineSubjectRepository.getTasksStream(subjectId)
            .filterNotNull()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun onEdit() {
        afterEdit(subjectDetailsState.value.subject!!.id)
    }

    fun onDelete() {
        if (subjectDetailsState.value.subject != null) viewModelScope.launch {
            offlineSubjectRepository.delete(subjectDetailsState.value.subject!!)
            afterDelete(subjectDetailsState.value.subject!!.id)
        }
    }
}

data class SubjectDetailsState(
    val subject: Subject? = null,
)