package com.lgaieta.classmanager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import com.lgaieta.classmanager.ui.theme.TopPagePadding

@Composable
fun RegisterPage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(
                start = HorizontalPagePadding,
                end = HorizontalPagePadding,
                top = TopPagePadding
            )
            .fillMaxWidth()
    ) {
        // Title and subtitle container
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.app_subtitle),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Spacer(modifier = Modifier.height(48.dp))
        RegisterForm()
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            ForgotPassword()
            AlreadyLoggedLink()
        }
    }
}

@Composable
fun AlreadyLoggedLink(modifier: Modifier = Modifier) {
    TextButton(
        onClick = { /* Handle go to login action */ },
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(R.string.change_to_login_text))
    }
}

@Composable
fun ForgotPassword(modifier: Modifier = Modifier) {
    TextButton(
        onClick = { /* Handle forgot password action */ },
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(R.string.forgot_password_text))
    }
}
