package com.lgaieta.classmanager.tasks.ui.new


import androidx.lifecycle.ViewModel
import com.lgaieta.classmanager.tasks.models.Task
import com.lgaieta.classmanager.tasks.models.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewTaskViewModel(
    private val offlineTaskRepository: TaskRepository,
    private val subjectId: Int,
    private val afterCreate: () -> Unit,
    private val afterCancel: () -> Unit
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewTaskState())
    val uiState: StateFlow<NewTaskState> = _uiState.asStateFlow()

    fun changeName(newName: String) {
        _uiState.update {
            it.copy(name = newName)
        }
    }

    fun changeDescription(newDesc: String){
        _uiState.update{
            it.copy(description = newDesc)
        }
    }

    private fun isNameValid(): Boolean {
        return _uiState.value.name.isNotBlank()
    }


    suspend fun saveTask() {
        if (isNameValid()) {
            offlineTaskRepository.insert(uiState.value.toTask().copy(subjectId = subjectId))
            _uiState.update {
                it.copy(name = "", description = "")
            }
            afterCreate()
        }
    }

    fun cancel() {
        afterCancel()
    }
}

data class NewTaskState(
    val name: String = "",
    val description: String = ""
) {
    fun toTask() = Task(id = 0, name =  name,description = description, subjectId = 0)
}
