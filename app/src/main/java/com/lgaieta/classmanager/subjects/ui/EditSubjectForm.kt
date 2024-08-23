package com.lgaieta.classmanager.subjects.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lgaieta.classmanager.R
import androidx.compose.ui.tooling.preview.Preview as Preview2

@Composable
fun EditSubjectForm(
    nameValue: String,
    onNameChange: (name: String) -> Unit,
    courseValue: String,
    onCourseChange:(course: String) -> Unit,
    onSubmit: () -> Unit,
    onCancel: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        NameFieldEdit(value = nameValue, onValueChange = onNameChange)
        CourseFieldEdit(value = courseValue,onValueChange = onCourseChange )
        timeSelect()
        Spacer(modifier = Modifier.weight(1f))
        SubmitEditButton(onClick = onSubmit, modifier = Modifier.fillMaxWidth())
        CancelEditButton(onClick = onCancel, modifier = Modifier.fillMaxWidth())
    }
}

@Preview2
@Composable
fun NameFieldEdit(value: String, onValueChange: (value: String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = stringResource(R.string.subject))
        OutlinedTextField(
            value = value,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            placeholder = { Text("Nombre de materia") },
            onValueChange = onValueChange
        )

    }
}
@Composable
fun CourseFieldEdit(value: String, onValueChange: (value: String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

        Text(text = stringResource(R.string.course_subject))
        OutlinedTextField(
            value = value,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            placeholder = { Text("Curso de materia") },
            onValueChange = onValueChange
        )

    }
}

@Composable
fun timeSelect() {
    Text(text = stringResource(R.string.time_label))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Lunes 15:30 - 17:30", fontSize = 16.sp)
            Box(
                modifier = Modifier
                    .size(24.dp)
            ) {
                IconButton(
                    onClick = { },

                    ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Eliminar horario",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
    Button(
        onClick = {},
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .padding(vertical = 4.dp)

    ) {

        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "",
            tint = Black
        )
        Text(
            text = stringResource(R.string.new_time_label),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = Black
        )
    }
}

@Composable
fun SubmitEditButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, modifier = modifier) {
        Text(text = stringResource(R.string.save_subject))
    }
}

@Composable
fun CancelEditButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
        ),
    ) {
        Text(
            text = stringResource(R.string.cancel),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = Black
        )
    }
}