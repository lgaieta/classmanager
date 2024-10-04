package com.lgaieta.classmanager.students.ui.new

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
import com.lgaieta.classmanager.subjects.models.Subject

@Composable
fun NewStudentForm(
    nameValue: String,
    onNameChange: (name: String) -> Unit,
    onSubmit: () -> Unit,
    availableSubjects: List<Subject>,
    selectedSubjects: List<Subject>,
    onSelectedSubjectsChange: (subjectList: List<Subject>) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        NameField(value = nameValue, onValueChange = onNameChange)
        NewStudentSubjectSelect(
            availableSubjects = availableSubjects,
            selectedSubjects = selectedSubjects,
            onSelectedSubjectsChange = onSelectedSubjectsChange
        )
        SubmitButton(onClick = onSubmit, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun NameField(value: String, onValueChange: (value: String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = stringResource(R.string.student_name_label))
        OutlinedTextField(
            value = value,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            onValueChange = onValueChange
        )
    }
}


@Composable
private fun SubmitButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        Text(text = stringResource(R.string.create_student))
    }
}