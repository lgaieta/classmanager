package com.lgaieta.classmanager.students.ui.edit

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.R
import com.lgaieta.classmanager.subjects.models.Subject

@Composable
fun EditStudentForm(
    nameValue: String,
    onNameChange: (name: String) -> Unit,
    onSubmit: () -> Unit,
    availableSubjects: List<Subject>,
    onSelectedSubjectsChange: (subjectList: List<Subject>) -> Unit,
    selectedSubjects: List<Subject>,
    onSubjectsToBeDeletedChange: (subjectList: List<Subject>) -> Unit,
    subjectsToBeDeleted: List<Subject>,
    onCancel: () -> Unit
) {
    Column {
        NameField(value = nameValue, onValueChange = onNameChange)
        Spacer(modifier = Modifier.height(24.dp))
        EditStudentSubjectSelect(
            availableSubjects = availableSubjects,
            onSelectedSubjectsChange = onSelectedSubjectsChange,
            selectedSubjects = selectedSubjects,
            subjectsToBeDeleted = subjectsToBeDeleted,
            onSubjectsToBeDeletedChange = onSubjectsToBeDeletedChange,
        )
        Spacer(modifier = Modifier.height(24.dp))
        SubmitButton(onClick = onSubmit, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(4.dp))
        CancelButton(onClick = onCancel, modifier = Modifier.fillMaxWidth())
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
                text = stringResource(R.string.student_name_label),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    )
}

@Composable
private fun SubmitButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick, modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        Text(text = stringResource(R.string.save_changes))
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