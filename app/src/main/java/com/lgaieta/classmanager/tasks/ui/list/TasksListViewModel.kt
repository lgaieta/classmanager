package com.lgaieta.classmanager.tasks.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lgaieta.classmanager.tasks.models.Task
import com.lgaieta.classmanager.tasks.models.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TasksListViewModel(offlineTaskRepository: TaskRepository) : ViewModel() {
    val taskListState: StateFlow<TaskListState> =
        offlineTaskRepository.getAllTasksStream().map { TaskListState(tasks = it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = TaskListState()
            )
}

data class TaskListState(
    val tasks: List<Task> = emptyList()
)
