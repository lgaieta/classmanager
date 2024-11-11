package com.lgaieta.classmanager.auth.ui.account

import androidx.lifecycle.ViewModel
import com.lgaieta.classmanager.models.SessionManager

class AccountViewModel(
    private val sessionManager: SessionManager,
    private val afterLogout: () -> Unit = {}
) : ViewModel() {
    fun logout() {
        sessionManager.logout()
        afterLogout()
    }
}