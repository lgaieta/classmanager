package com.lgaieta.classmanager.tasks.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lgaieta.classmanager.tasks.models.Task
import com.lgaieta.classmanager.tasks.models.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskDetailsViewModel(
    private val offlineTaskRepository: TaskRepository,
    taskId: Int,
    private val afterEdit: (id: Int) -> Unit = {},
    private val afterDelete: (id: Int) -> Unit = {}
) : ViewModel() {
    val taskDetailsState: StateFlow<TaskDetailsState> =
        offlineTaskRepository.getTaskStream(taskId)
            .filterNotNull()
            .map { task -> TaskDetailsState(task) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = TaskDetailsState()
            )

    fun onEdit() {
        afterEdit(taskDetailsState.value.task!!.id)
    }

    fun onDelete() {
        if (taskDetailsState.value.task != null) viewModelScope.launch {
            offlineTaskRepository.delete(taskDetailsState.value.task!!)
            afterDelete(taskDetailsState.value.task!!.id)
        }
    }
}

data class TaskDetailsState(
    val task: Task? = null,
)