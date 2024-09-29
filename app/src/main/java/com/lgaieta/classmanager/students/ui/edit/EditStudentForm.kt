package com.lgaieta.classmanager.students.ui.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.R

@Composable
fun EditStudentForm(
    nameValue: String,
    onNameChange: (name: String) -> Unit,
    onSubmit: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        NameFieldEdit(value = nameValue, onValueChange = onNameChange)
        SubmitEditButton(onClick = onSubmit, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun NameFieldEdit(value: String, onValueChange: (value: String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = stringResource(R.string.student_name_label))
        OutlinedTextField(
            value = value,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            placeholder = { Text(stringResource(R.string.student_name_placeholder)) },
            onValueChange = onValueChange
        )
    }
}

@Composable
fun SubmitEditButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick, modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        Text(text = stringResource(R.string.save_changes))
    }
}