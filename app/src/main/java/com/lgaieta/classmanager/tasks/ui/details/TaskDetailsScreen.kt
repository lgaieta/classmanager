package com.lgaieta.classmanager.tasks.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.lgaieta.classmanager.R
import com.lgaieta.classmanager.ui.BottomNavBar
import com.lgaieta.classmanager.ui.BottomNavBarActions
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import com.lgaieta.classmanager.ui.theme.TopPagePadding
import kotlinx.coroutines.launch
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.lgaieta.classmanager.students.models.Student
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.saveable.rememberSaveable
import com.lgaieta.classmanager.students.models.StudentWithNote
import com.lgaieta.classmanager.ui.theme.BottomPagePadding

@Composable
fun TaskDetailsScreen(
    taskDetailsViewModel: TaskDetailsViewModel,
    bottomNavBarActions: BottomNavBarActions,
    modifier: Modifier = Modifier,
) {
    val taskState by taskDetailsViewModel.taskDetailsState.collectAsState()
    val subjectState by taskDetailsViewModel.subjectState.collectAsState()
    val studentsState by taskDetailsViewModel.studentsState.collectAsState()
    val isNotFound = taskState == null
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        bottomBar = { BottomNavBar(bottomNavBarActions) }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(
                    start = HorizontalPagePadding,
                    end = HorizontalPagePadding,
                )
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(TopPagePadding + innerPadding.calculateTopPadding()))
            if (!isNotFound) {
                if (subjectState != null) {
                    SubjectBadge(name = subjectState!!.name)
                }
                Spacer(modifier = Modifier.height(16.dp))
                TaskDetailsHeader(title = taskState!!.name)
                Spacer(modifier = Modifier.height(36.dp))
                TaskDetailsButtons(
                    onEdit = { taskDetailsViewModel.onEdit() },
                    onDelete = { coroutineScope.launch { taskDetailsViewModel.onDelete() } },
                )
                Spacer(modifier = Modifier.height(24.dp))
                if (taskState!!.description.isNotEmpty()) {
                    TaskDescription(description = taskState!!.description)
                    Spacer(modifier = Modifier.height(24.dp))
                }
                Text(
                    text = stringResource(R.string.notes),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                )
                Spacer(modifier = Modifier.height(4.dp))
                TaskDetailsStudents(
                    students = studentsState,
                    onUpdateStudentNote = { studentWithNote ->
                        taskDetailsViewModel.changeNote(studentWithNote)
                    },
                )
            } else {
                TaskDetailsNotFound()
            }
            Spacer(modifier = Modifier.height(BottomPagePadding + innerPadding.calculateBottomPadding()))
        }
    }
}

@Composable
private fun TaskDescription(description: String) {
    Column {
        Text(
            text = stringResource(R.string.description),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Composable
private fun TaskDetailsNotFound() {
    Text(
        text = stringResource(R.string.task_not_found),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .padding(24.dp)
    )
}

@Composable
private fun SubjectBadge(name: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 8.dp, vertical = 2.dp)
        )
    }
}

@Composable
fun TaskDetailsHeader(title: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun TaskDetailsStudents(
    students: List<StudentWithNote>,
    onUpdateStudentNote: (StudentWithNote) -> Unit
) {
    var selectedStudentWithNote by rememberSaveable { mutableStateOf<StudentWithNote?>(null) }
    var showNoteSelector by rememberSaveable { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 400.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(students) { studentWithNote ->
            StudentCard(
                studentWithNote = studentWithNote,
                onClick = {
                    selectedStudentWithNote = studentWithNote
                    showNoteSelector = true
                }
            )
        }
    }

    if (showNoteSelector) {
        selectedStudentWithNote?.let {
            ShowNoteSelectorDialog(
                studentWithNote = it,
                onSaveNote = { note ->
                    onUpdateStudentNote(StudentWithNote(student = it.student, note = note))
                    showNoteSelector = false
                },
                onDismiss = { showNoteSelector = false }
            )
        }
    }
}


@Composable
fun ShowNoteSelectorDialog(
    studentWithNote: StudentWithNote,
    onSaveNote: (Float) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = stringResource(R.string.select_note_for) + "  ${studentWithNote.student.name}") },
        text = {
            NoteSelector(
                initialNote = studentWithNote.note,
                onSaveNote = onSaveNote
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    )
}

@Composable
fun NoteSelector(initialNote: Float?, onSaveNote: (Float) -> Unit) {
    var note by rememberSaveable { mutableStateOf(initialNote) }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = note.toString(),
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { if (note != null) onSaveNote(note!!) },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.save_note))
        }
    }
}

@Composable
private fun StudentCard(studentWithNote: StudentWithNote, onClick: () -> Unit) {
    val note = studentWithNote.note

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = studentWithNote.student.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(8.dp)
            )
            Box(
                modifier = Modifier
                    .size(32.dp)
            ) {
                Text(
                    text = if (note == null || note <= 0f) "0" else note.toString(),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
