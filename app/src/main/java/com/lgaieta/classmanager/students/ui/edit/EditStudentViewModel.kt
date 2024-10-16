package com.lgaieta.classmanager.students.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lgaieta.classmanager.students.models.Student
import com.lgaieta.classmanager.students.models.StudentRepository
import com.lgaieta.classmanager.subjects.models.Subject
import com.lgaieta.classmanager.subjects.models.SubjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditStudentViewModel(
    private val offlineStudentRepository: StudentRepository,
    private val offlineSubjectRepository: SubjectRepository,
    private val studentId: Long,
    private val afterEdit: (id: Long) -> Unit = {},
) : ViewModel() {
    private val _editStudentState = MutableStateFlow(EditStudentState())

    val editStudentState: StateFlow<EditStudentState> = _editStudentState.asStateFlow()

    val availableSubjects: StateFlow<List<Subject>> =
        offlineSubjectRepository.getAllSubjectsStream().filterNotNull().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        viewModelScope.launch {
            combine(
                offlineStudentRepository.getStudentStream(studentId),
                offlineStudentRepository.getSubjectsStream(studentId)
            ) { student, subjects ->
                EditStudentState(
                    name = student?.name ?: "",
                    storedStudent = student,
                    selectedSubjects = subjects
                )
            }.collect { editStudentState ->
                _editStudentState.value = editStudentState
            }
        }

    }

    fun changeName(newName: String) {
        _editStudentState.update {
            it.copy(name = newName)
        }
    }

    suspend fun editStudent() {
        val currentState = _editStudentState.value
        val updatedStudent = currentState.storedStudent?.copy(
            name = currentState.name,
        )
        if (updatedStudent != null) {
            offlineStudentRepository.removeSubjects(_editStudentState.value.subjectsToBeDeleted.map {
                Pair(
                    studentId,
                    it.id
                )
            })
            offlineStudentRepository.assignSubjects(_editStudentState.value.selectedSubjects.map {
                Pair(
                    studentId,
                    it.id
                )
            })
            offlineStudentRepository.update(updatedStudent)
            _editStudentState.update {
                it.copy(
                    name = "",
                    storedStudent = null,
                    selectedSubjects = emptyList(),
                    subjectsToBeDeleted = emptyList()
                )
            }
            afterEdit(updatedStudent.id)
        }
    }

    fun onSelectedSubjectsChange(newList: List<Subject>) {
        _editStudentState.update {
            it.copy(
                selectedSubjects = newList
            )
        }
    }

    fun onSubjectsToBeDeletedChange(newList: List<Subject>) {
        _editStudentState.update {
            it.copy(
                subjectsToBeDeleted = newList
            )
        }
    }
}

data class EditStudentState(
    val name: String = "",
    val storedStudent: Student? = null,
    val selectedSubjects: List<Subject> = emptyList(),
    val subjectsToBeDeleted: List<Subject> = emptyList()
)