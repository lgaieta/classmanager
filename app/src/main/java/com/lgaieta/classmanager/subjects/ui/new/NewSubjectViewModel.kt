package com.lgaieta.classmanager.subjects.ui.new

import androidx.lifecycle.ViewModel
import com.lgaieta.classmanager.subjects.models.Subject
import com.lgaieta.classmanager.subjects.models.SubjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewSubjectViewModel(
    private val offlineSubjectRepository: SubjectRepository,
    private val afterCancel: () -> Unit,
    private val afterSave: () -> Unit,
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewSubjectState())
    val uiState: StateFlow<NewSubjectState> = _uiState.asStateFlow()

    fun changeName(newName: String) {
        _uiState.update {
            it.copy(name = newName)
        }
    }

    fun changeCourse(newCourse: String) {
        _uiState.update {
            it.copy(course = newCourse)
        }
    }

    fun cancel() {
        _uiState.update {
            it.copy(name = "", course = "")
        }
        afterCancel()
    }

    private fun isNameValid(): Boolean {
        return _uiState.value.name.isNotBlank()
    }

    private fun isCourseValid(): Boolean {
        return _uiState.value.course.isNotBlank()
    }

    suspend fun saveSubject() {
        if (isNameValid() && isCourseValid()) {
            offlineSubjectRepository.insert(uiState.value.toSubject())
            _uiState.update {
                it.copy(name = "", course = "")
            }
            afterSave()
        }
    }
}

data class NewSubjectState(
    val name: String = "",
    val course: String = ""
) {
    fun toSubject() = Subject(id = 0, name = name, course = course)
}