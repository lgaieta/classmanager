package com.lgaieta.classmanager.tasks.ui.new


import androidx.lifecycle.ViewModel
import com.lgaieta.classmanager.tasks.models.Task
import com.lgaieta.classmanager.tasks.models.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewTaskViewModel(
    private val offlineTaskRepository: TaskRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewTaskState())
    val uiState: StateFlow<NewTaskState> = _uiState.asStateFlow()

    fun changeName(newName: String) {
        _uiState.update {
            it.copy(name = newName)
        }
    }

    private fun isNameValid(): Boolean {
        return _uiState.value.name.isNotBlank()
    }

    suspend fun saveTask() {
        if (isNameValid()) {
            offlineTaskRepository.insert(uiState.value.toTask())
            _uiState.update {
                it.copy(name = "")
            }
        }
    }
}

data class NewTaskState(
    val name: String = ""
) {
    fun toTask() = Task(id = 0, name =  name)
}