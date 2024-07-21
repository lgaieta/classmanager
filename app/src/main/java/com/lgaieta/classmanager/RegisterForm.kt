package com.lgaieta.classmanager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp

@Composable
fun RegisterForm() {
    Column(verticalArrangement = Arrangement.spacedBy(32.dp)) {
        Text(text="Cree su cuenta para empezar")
    }
}