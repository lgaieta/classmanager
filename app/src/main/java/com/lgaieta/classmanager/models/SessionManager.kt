package com.lgaieta.classmanager.models

import com.google.firebase.auth.FirebaseAuth

private val auth: FirebaseAuth = FirebaseAuth.getInstance()

class SessionManager {
    fun registerWithEmailAndPassword(
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
}
