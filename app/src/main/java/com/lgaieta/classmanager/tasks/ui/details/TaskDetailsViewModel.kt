package com.lgaieta.classmanager.tasks.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lgaieta.classmanager.students.models.StudentRepository
import com.lgaieta.classmanager.students.models.StudentWithNote
import com.lgaieta.classmanager.subjects.models.Subject
import com.lgaieta.classmanager.subjects.models.SubjectRepository
import com.lgaieta.classmanager.tasks.models.Task
import com.lgaieta.classmanager.tasks.models.TaskRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskDetailsViewModel(
    private val offlineTaskRepository: TaskRepository,
    private val offlineStudentRepository: StudentRepository,
    offlineSubjectRepository: SubjectRepository,
    private val taskId: Int,
    private val afterEdit: (id: Int) -> Unit = {},
    private val afterDelete: (task: Task) -> Unit = {},
    private val afterUpdateNote: (studentWithNote: StudentWithNote) -> Unit = {}
) : ViewModel() {
    val taskDetailsState: StateFlow<Task?> =
        offlineTaskRepository.getTaskStream(taskId)
            .filterNotNull()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = null
            )

    @OptIn(ExperimentalCoroutinesApi::class)
    val subjectState: StateFlow<Subject?> =
        taskDetailsState
            .filterNotNull()
            .flatMapLatest { task ->
                offlineSubjectRepository.getSubjectStream(task.subjectId)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = null
            )

    val studentsState: StateFlow<List<StudentWithNote>> =
        offlineStudentRepository.getByTask(taskId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            ).also {
                println("Students: ${it.value}")
            }

    suspend fun saveNote(studentWithNote: StudentWithNote) {
        offlineStudentRepository.updateNote(studentWithNote, taskId)
        afterUpdateNote(studentWithNote)
    }

    fun onEdit() {
        afterEdit(taskId)
    }

    fun onDelete() {
        if (taskDetailsState.value != null) viewModelScope.launch {
            offlineTaskRepository.delete(taskDetailsState.value!!)
            afterDelete(taskDetailsState.value!!)
        }
    }
}

