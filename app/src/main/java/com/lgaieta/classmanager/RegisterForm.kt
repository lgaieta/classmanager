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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun RegisterForm(modifier: Modifier = Modifier) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(24.dp), modifier = modifier) {
        RegisterFormTitle()
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = stringResource(R.string.email_label))
            OutlinedTextField(
                value = email,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                onValueChange = { email = it }
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = stringResource(R.string.password_label))
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
                text = stringResource(R.string.register_button_text),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun RegisterFormTitle(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.register_form_title),
        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
        modifier = modifier
    )
}