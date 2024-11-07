import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lgaieta.classmanager.subjects.models.Subject
import com.lgaieta.classmanager.subjects.models.SubjectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditSubjectViewModel(
    private val offlineSubjectRepository: SubjectRepository,
    subjectId: Int,
    private val afterEdit: (id: Int) -> Unit = {},
    private val afterCancel: () -> Unit
) : ViewModel() {
    private val _editSubjectState = MutableStateFlow(EditSubjectState())

    val editSubjectState: StateFlow<EditSubjectState> = _editSubjectState.asStateFlow()

    init {
        viewModelScope.launch {
            offlineSubjectRepository.getSubjectStream(subjectId)
                .collect { subject ->
                    _editSubjectState.value = EditSubjectState(
                        name = subject?.name ?: "",
                        info = subject?.info ?: "",
                        storedSubject = subject
                    )
                }
        }
    }

    fun changeName(newName: String) {
        _editSubjectState.update {
            it.copy(name = newName)
        }
    }

    fun changeInfo(newInfo: String) {
        _editSubjectState.update {
            it.copy(info = newInfo)
        }
    }

    suspend fun editSubject() {
        val currentState = _editSubjectState.value
        val updatedSubject = currentState.storedSubject?.copy(
            name = currentState.name,
            info = currentState.info
        )
        if (updatedSubject != null) {
            offlineSubjectRepository.update(updatedSubject)
            afterEdit(updatedSubject.id)
        }
    }

    fun cancel() {
        afterCancel()
    }
}

data class EditSubjectState(
    val name: String = "",
    val info: String = "",
    val storedSubject: Subject? = null,
)