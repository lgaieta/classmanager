import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lgaieta.classmanager.tasks.models.Task
import com.lgaieta.classmanager.tasks.models.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditTaskViewModel(
    private val offlineTaskRepository: TaskRepository,
    taskId: Int,
    private val afterEdit: (id: Int) -> Unit = {},
) : ViewModel() {
    private val _editTaskState = MutableStateFlow(EditTaskState())

    val editTaskState: StateFlow<EditTaskState> = _editTaskState.asStateFlow()

    init {
        viewModelScope.launch {
            offlineTaskRepository.getTaskStream(taskId)
                .collect { task ->
                    _editTaskState.value = EditTaskState(
                        name = task?.name ?: "",
                        storedTask = task
                    )
                }
        }
    }

    fun changeName(newName: String) {
        _editTaskState.update {
            it.copy(name = newName)
        }
    }


    suspend fun editTask() {
        val currentState = _editTaskState.value
        val updatedTask = currentState.storedTask?.copy(
            name = currentState.name,
        )
        if (updatedTask != null) {
            offlineTaskRepository.update(updatedTask)
            afterEdit(updatedTask.id)
        }
    }
}

data class EditTaskState(
    val name: String = "",
    val storedTask: Task? = null,
)