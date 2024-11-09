package com.lgaieta.classmanager.subjects.ui.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.R
import com.lgaieta.classmanager.students.models.Student

@Composable
fun SubjectDetailsStudents(
    onStudentClick: (student: Student) -> Unit,
    onDeleteStudent: (student: Student) -> Unit,
    modifier: Modifier = Modifier,
    students: List<Student> = emptyList(),
) {
    Text(
        text = stringResource(R.string.students),
        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
        modifier = modifier
    )
    Spacer(modifier = Modifier.height(4.dp))
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(students) { student ->
            Card(
                onClick = { onStudentClick(student) },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer.copy(
                        alpha = 0.5f
                    )
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.surfaceContainerHigh
                ),
                shape = MaterialTheme.shapes.large
            ) {
                Box(
                    modifier = Modifier
                        .widthIn(max = 150.dp),
                ) {
                    Text(
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        text = student.name,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(32.dp)
                    )
                    SubjectDetailsRemoveStudentButton(
                        onDelete = { onDeleteStudent(student) },
                        modifier = Modifier.align(Alignment.TopEnd).padding(8.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun NewStudentButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick, modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.Add, stringResource(R.string.new_student))
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(R.string.new_student),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}