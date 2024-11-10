package com.lgaieta.classmanager.auth.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.R

@Composable
fun LoginForm(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onRegister: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Title()
        Spacer(modifier = Modifier.height(8.dp))
        EmailField(email, onEmailChange)
        Spacer(modifier = Modifier.height(16.dp))
        PasswordField(password, onPasswordChange)
        Spacer(modifier = Modifier.height(16.dp))
        SubmitButton(onSubmit)
        Spacer(modifier = Modifier.height(8.dp))
        RegisterButton(onClick = onRegister)
    }
}

@Composable
private fun Title() {
    Text(
        text = stringResource(R.string.login),
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
    )
}


@Composable
private fun EmailField(email: String, onEmailChange: (String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(
                stringResource(R.string.email),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    )
}

@Composable
private fun PasswordField(password: String, onPasswordChange: (String) -> Unit) {
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation(),
        label = {
            Text(
                stringResource(R.string.password),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    )
}

@Composable
private fun SubmitButton(onSubmit: () -> Unit) {
    Button(
        onClick = { onSubmit() },
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            stringResource(R.string.login),
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
private fun RegisterButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    TextButton(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.dont_have_account),
            modifier = Modifier.padding(8.dp)
        )
    }
}