package com.lgaieta.classmanager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun RegisterForm(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(24.dp), modifier = modifier) {
        Text(
            text = "Cree su cuenta para empezar",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = "Correo electrónico")
            OutlinedTextField(
                value = email,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                onValueChange = { email = it }
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = "Contraseña")
            OutlinedTextField(
                value = password,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { password = it }
            )
        }
        Button(
            onClick = { /* Handle register action */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(16.dp),
        ) {
            Text(
                text = "Crear cuenta",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            TextButton(
                onClick = { /* Handle go to login action */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "¿Ya tenés una cuenta? Iniciá sesión")
            }
            TextButton(
                onClick = { /* Handle forgot password action */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Olvidé mi contraseña")
            }
        }
    }
}