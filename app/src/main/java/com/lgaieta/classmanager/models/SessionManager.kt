package com.lgaieta.classmanager.models

interface SessionManager {
    fun registerWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    )

    fun loginWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    )

    fun isLoggedIn(): Boolean
}