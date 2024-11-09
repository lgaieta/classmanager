package com.lgaieta.classmanager.students.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.lgaieta.classmanager.R
import com.lgaieta.classmanager.students.models.Student

@Composable
fun SubjectDetailsStudentSelector(
    availableStudents: List<Student>,
    onSelectedStudentsChange: (studentList: List<Student>) -> Unit,
    selectedStudents: List<Student>
) {
    var isOpen by rememberSaveable {
        mutableStateOf(false)
    }
    var selectedDialogStudents by rememberSaveable {
        mutableStateOf(selectedStudents)
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Button(
            onClick = { isOpen = true }, modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(R.string.add_students)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = stringResource(R.string.add_students))
        }
    }
    if (isOpen) Dialog(onDismissRequest = {
        isOpen = false
        selectedDialogStudents = selectedStudents
    }) {
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Column {
                Text(
                    text = stringResource(R.string.student_select_title),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                )
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .weight(1f)
                ) {
                    item {
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                    items(availableStudents) { availableStudent ->
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                                contentColor = MaterialTheme.colorScheme.onSurface,
                            ),
                            onClick = {
                                if (!selectedDialogStudents.contains(availableStudent))
                                    selectedDialogStudents += availableStudent
                                else
                                    selectedDialogStudents =
                                        selectedDialogStudents.filterNot { it == availableStudent }
                            }) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = availableStudent.name,
                                    modifier = Modifier
                                        .padding(16.dp),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                if (selectedDialogStudents.contains(availableStudent)) {
                                    Icon(
                                        Icons.Filled.Done,
                                        contentDescription = stringResource(
                                            R.string.selected_label
                                        ),
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .size(16.dp),
                                    )
                                }
                            }
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
                HorizontalDivider(modifier = Modifier.fillMaxWidth())
                Row(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)) {
                    Button(
                        onClick = {
                            onSelectedStudentsChange(selectedDialogStudents)
                            isOpen = false
                        },
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.add_students),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

