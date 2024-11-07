package com.lgaieta.classmanager.subjects.ui.new

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.R

@Composable
fun NewSubjectForm(
    nameValue: String,
    onNameChange: (name: String) -> Unit,
    infoValue: String,
    onInfoChange: (info: String) -> Unit,
    onSubmit: () -> Unit,
    onCancel: () -> Unit
) {
    Column {
        NameField(value = nameValue, onValueChange = onNameChange)
        Spacer(modifier = Modifier.height(20.dp))
        InfoField(value = infoValue, onValueChange = onInfoChange)
        Spacer(modifier = Modifier.height(32.dp))
        SubmitButton(onClick = onSubmit, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(4.dp))
        CancelButton(onClick = onCancel, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        HelperText(text = stringResource(R.string.new_subject_helper_text))
    }
}

@Composable
private fun NameField(value: String, onValueChange: (value: String) -> Unit) {
    OutlinedTextField(
        value = value,
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        onValueChange = onValueChange,
        label = {
            Text(
                text = stringResource(R.string.subject_name_label),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    )
}

@Composable
private fun InfoField(value: String, onValueChange: (value: String) -> Unit) {
    OutlinedTextField(
        value = value,
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        onValueChange = onValueChange,
        label = {
            Text(
                text = stringResource(R.string.info_label),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        supportingText = { Text(text = stringResource(R.string.optional)) }
    )
}

@Composable
private fun SubmitButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        Text(text = stringResource(R.string.create_subject))
    }
}

@Composable
private fun CancelButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        Text(text = stringResource(R.string.cancel))
    }
}

@Composable
private fun HelperText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.secondary
    )
}