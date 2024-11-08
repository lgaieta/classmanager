package com.lgaieta.classmanager.tasks.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lgaieta.classmanager.students.models.Student
import com.lgaieta.classmanager.students.models.StudentRepository
import com.lgaieta.classmanager.subjects.models.Subject
import com.lgaieta.classmanager.tasks.models.Task
import com.lgaieta.classmanager.tasks.models.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskDetailsViewModel(
    private val offlineTaskRepository: TaskRepository,
    private val offlineStudentRepository: StudentRepository,
    subjectId: Int,
    taskId: Int,
    private val afterEdit: (id: Int) -> Unit = {},
    private val afterDelete: (task: Task) -> Unit = {}
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

    val subjectState: StateFlow<Subject?> =
        offlineTaskRepository.getSubjectStream(taskId)
            .filterNotNull()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = null
            )
    val studentsState: StateFlow<List<Student>> =
        offlineStudentRepository.getStudentsTaskStream(subjectId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )



    fun onEdit() {
        afterEdit(taskDetailsState.value.task!!.id)
    }

    fun onDelete() {
        if (taskDetailsState.value.task != null) viewModelScope.launch {
            offlineTaskRepository.delete(taskDetailsState.value.task!!)
            afterDelete(taskDetailsState.value.task!!)
        }
    }
}

data class TaskDetailsState(
    val task: Task? = null,
)
