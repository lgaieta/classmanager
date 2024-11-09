package com.lgaieta.classmanager.tasks.ui.details

import com.lgaieta.classmanager.students.models.Student
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.R

@Composable
fun TaskDetailsStudents(students: List<Student>) {
    if (students.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 400.dp), 
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(students) { student ->
                StudentCard(student = student)
            }
        }
    } else {
        Text(
            text = stringResource(R.string.students_not_found),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(24.dp)
        )
    }
}


@Composable
fun StudentCard(student: Student) {
    val noteBackgroundColor = when {
        student.note >= 7 -> Color.Green
        student.note in 0..6 -> Color.Red
        else -> Color.Gray
    }

    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = student.name,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.weight(1f)
            )

            Box(
                modifier = Modifier
                    .background(noteBackgroundColor, shape = RoundedCornerShape(4.dp))
                    .padding(8.dp)
            ) {
                Text(
                    text = student.note.toString(),
                    style = MaterialTheme.typography.labelLarge.copy(color = Color.White)
                )
            }
        }
    }
}
