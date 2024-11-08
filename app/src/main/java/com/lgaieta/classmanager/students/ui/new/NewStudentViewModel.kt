package com.lgaieta.classmanager.students.ui.new

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
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class NewStudentViewModel(
    private val offlineStudentRepository: StudentRepository,
    offlineSubjectRepository: SubjectRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewStudentState())
    val uiState: StateFlow<NewStudentState> = _uiState.asStateFlow()

    val availableSubjects: StateFlow<List<Subject>> =
        offlineSubjectRepository.getAllSubjectsStream().filterNotNull().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun changeName(newName: String) {
        _uiState.update {
            it.copy(name = newName)
        }
    }

    private fun isNameValid(): Boolean {
        return _uiState.value.name.isNotBlank()
    }

    fun onSelectedSubjectsChange(newList: List<Subject>) {
        _uiState.update {
            it.copy(selectedSubjects = newList)
        }
    }

    suspend fun saveStudent() {
        if (isNameValid()) {
            val studentId = offlineStudentRepository.insert(uiState.value.toStudent())
            offlineStudentRepository.assignSubjects(uiState.value.selectedSubjects.map { Pair(studentId, it.id) })
            _uiState.update {
                it.copy(name = "", selectedSubjects = emptyList())
            }
        }
    }
}

data class NewStudentState(
    val name: String = "",
    val selectedSubjects: List<Subject> = emptyList()
) {
    fun toStudent() = Student(id = 0, name = name, note = 0)
}