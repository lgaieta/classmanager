package com.lgaieta.classmanager.students.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lgaieta.classmanager.students.models.StudentRepository
import com.lgaieta.classmanager.students.models.Student
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StudentDetailsViewModel(
    private val offlineStudentRepository: StudentRepository,
    studentId: Int,
    private val afterEdit: (id: Int) -> Unit = {},
    private val afterDelete: (id: Int) -> Unit = {}
) : ViewModel() {
    val studentDetailsState: StateFlow<StudentDetailsState> =
        offlineStudentRepository.getStudentStream(studentId)
            .filterNotNull()
            .map { student -> StudentDetailsState(student) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = StudentDetailsState()
            )

    fun onEdit() {
        afterEdit(studentDetailsState.value.student!!.id)
    }

    fun onDelete() {
        if (studentDetailsState.value.student != null) viewModelScope.launch {
            offlineStudentRepository.delete(studentDetailsState.value.student!!)
            afterDelete(studentDetailsState.value.student!!.id)
        }
    }
}

data class StudentDetailsState(
    val student: Student? = null,
)