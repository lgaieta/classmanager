package com.lgaieta.classmanager.students.ui.new

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.lgaieta.classmanager.subjects.models.Subject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewStudentSubjectSelect(
    availableSubjects: List<Subject>,
    onSelectedSubjectsChange: (subjectList: List<Subject>) -> Unit,
    selectedSubjects: List<Subject>
) {
    var isOpen by rememberSaveable {
        mutableStateOf(false)
    }
    var selectedDialogSubjects by rememberSaveable {
        mutableStateOf(selectedSubjects)
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = stringResource(R.string.subjects),
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
        )
        if (selectedSubjects.isEmpty()) {
            Text(
                text = "Haz click en el botón + para elegir las materias del alumno.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            selectedSubjects.map { selectedSubject ->
                Card {
                    Text(
                        text = selectedSubject.name,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
    Button(onClick = { isOpen = true }, modifier = Modifier.fillMaxWidth()) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_subjects)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = stringResource(R.string.add_subjects))
    }
    if (isOpen) Dialog(onDismissRequest = { isOpen = false }) {
        Card {
            Column {
                Text(
                    text = stringResource(R.string.subject_select_title),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                )
                Divider(modifier = Modifier.fillMaxWidth())
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .weight(1f)
                ) {
                    items(availableSubjects) { availableSubject ->
                        Card(
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary),
                            onClick = {
                                if (!selectedDialogSubjects.contains(availableSubject))
                                    selectedDialogSubjects += availableSubject
                                else
                                    selectedDialogSubjects =
                                        selectedDialogSubjects.filterNot { it == availableSubject }
                            }) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = availableSubject.name,
                                    modifier = Modifier
                                        .padding(16.dp),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                if (selectedDialogSubjects.contains(availableSubject)) {
                                    Icon(
                                        Icons.Filled.Done,
                                        contentDescription = stringResource(
                                            R.string.selected
                                        ),
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .size(16.dp),
                                    )
                                }
                            }
                        }
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth())
                Row(modifier = Modifier.padding(24.dp)) {
                    Button(
                        onClick = {
                            onSelectedSubjectsChange(selectedDialogSubjects)
                            isOpen = false
                        },
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.add_subjects),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

