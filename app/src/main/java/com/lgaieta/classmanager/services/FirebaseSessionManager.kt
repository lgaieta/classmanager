package com.lgaieta.classmanager.services

import com.google.firebase.auth.FirebaseAuth
import com.lgaieta.classmanager.models.SessionManager

private val auth = FirebaseAuth.getInstance()

class FirebaseSessionManager : SessionManager {
    override fun registerWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess()
            } else {
                onFailure()
            }
        }
    }

    override fun loginWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSuccess()
            } else {
                onFailure()
            }
        }
    }

    override fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }
}