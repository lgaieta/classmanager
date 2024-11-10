package com.lgaieta.classmanager.auth.ui.register

import androidx.lifecycle.ViewModel
import com.lgaieta.classmanager.models.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel(
    private val sessionManager: SessionManager,
    private val afterRegister: () -> Unit = {},
    private val onLoginClick: () -> Unit = {}
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun onEmailChange(email: String) = _state.update { it.copy(email = email) }
    fun onPasswordChange(password: String) = _state.update { it.copy(password = password) }

    fun onSubmit() {
        if (_state.value.email.isEmpty() || _state.value.password.isEmpty()) {
            _state.update { it.copy(error = "Debe completar todos los campos.") }
            return
        }
        sessionManager.registerWithEmailAndPassword(
            _state.value.email,
            _state.value.password,
            onFailure = {
                _state.update { it.copy(error = "Ocurrió un error.") }
            },
            onSuccess = {
                _state.update { it.copy(error = null) }
                afterRegister()
            }
        )
    }

    fun onLogin() {
        onLoginClick()
    }
}

data class RegisterState(
    val email: String = "",
    val password: String = "",
    val error: String? = null
)