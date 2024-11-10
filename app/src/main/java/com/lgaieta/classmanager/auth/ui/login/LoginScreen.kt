package com.lgaieta.classmanager.auth.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.R
import com.lgaieta.classmanager.auth.ui.register.LoginViewModel
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import com.lgaieta.classmanager.ui.theme.TopPagePadding

@Composable
fun LoginScreen(modifier: Modifier = Modifier, loginViewModel: LoginViewModel) {
    val state by loginViewModel.state.collectAsState()

    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .padding(
                    start = HorizontalPagePadding,
                    end = HorizontalPagePadding,
                    bottom = innerPadding.calculateBottomPadding()
                )
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(TopPagePadding + innerPadding.calculateTopPadding()))
            Title()
            Spacer(modifier = Modifier.height(40.dp))
            LoginForm(
                email = state.email,
                onEmailChange = { loginViewModel.onEmailChange(it) },
                password = state.password,
                onPasswordChange = { loginViewModel.onPasswordChange(it) },
                onSubmit = { loginViewModel.onSubmit() },
                onRegister = {loginViewModel.onRegister()}
            )
            Spacer(modifier = Modifier.height(16.dp))
            state.error?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun Title() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.classmanager),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.main_subtitle),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth()
        )
    }
}