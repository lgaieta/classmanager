package com.lgaieta.classmanager.tasks.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.students.models.Student
import com.lgaieta.classmanager.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.saveable.rememberSaveable

//@Composable
//fun TaskDetailsStudents(students: List<Student>, onUpdateStudentNote: (Int) -> Unit) {
//    var selectedStudent by remember { mutableStateOf<Student?>(null) }
//    var showNoteSelector by remember { mutableStateOf(false) }
//
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxWidth()
//            .heightIn(max = 400.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        items(students) { student ->
//            StudentCard(
//                student = student,
//                onClick = {
//                    selectedStudent = student
//                    showNoteSelector = true
//                }
//            )
//        }
//    }
//
//    selectedStudent?.let {
//        if (showNoteSelector) {
//            ShowNoteSelectorDialog(
//                student = it,
//                onSaveNote = { note ->
//                    onUpdateStudentNote(note)
//                    showNoteSelector = false
//                },
//                onDismiss = { showNoteSelector = false }
//            )
//        }
//    }
//}
//
//@Composable
//fun ShowNoteSelectorDialog(
//    student: Student,
//    onSaveNote: (Int) -> Unit,
//    onDismiss: () -> Unit
//) {
//    AlertDialog(
//        onDismissRequest = onDismiss,
//        title = { Text(text = "Seleccionar nota para ${student.name}") },
//        text = {
//            NoteSelector(
//                initialNote = student.note,
//                onSaveNote = onSaveNote
//            )
//        },
//        confirmButton = {
//            TextButton(onClick = onDismiss) {
//                Text(text = "Cerrar")
//            }
//        }
//    )
//}
//
//@Composable
//fun NoteSelector(initialNote: Int, onSaveNote: (Int) -> Unit) {
//    var note by rememberSaveable { mutableStateOf(initialNote) }
//
//    Column(
//        modifier = Modifier.padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            IconButton(
//                onClick = { if (note > 0) note-- },
//                modifier = Modifier.size(40.dp)
//            ) {
//                Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Reducir nota")
//            }
//
//            Text(
//                text = note.toString(),
//                style = MaterialTheme.typography.displayMedium,
//                modifier = Modifier.padding(horizontal = 16.dp)
//            )
//
//            IconButton(
//                onClick = { if (note < 10) note++ },
//                modifier = Modifier.size(40.dp)
//            ) {
//                Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Aumentar nota")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = { onSaveNote(note) },
//            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
//        ) {
//            Text(text = "Guardar nota")
//        }
//    }
//}
//
//@Composable
//fun StudentCard(student: Student, onClick: () -> Unit) {
//    val noteBackgroundColor = when {
//        student.note >= 7 -> Color.Green
//        student.note in 0..6 -> Color.Red
//        else -> Color.Gray
//    }
//
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 8.dp)
//            .clickable { onClick() }
//    ) {
//        Row(
//            modifier = Modifier
//                .padding(8.dp)
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = student.name,
//                style = MaterialTheme.typography.labelLarge,
//                modifier = Modifier.padding(8.dp)
//            )
//
//            Box(
//                modifier = Modifier
//                    .background(noteBackgroundColor, shape = RoundedCornerShape(8.dp))
//                    .padding(12.dp)
//            ) {
//                Text(
//                    text = student.note.toString(),
//                    style = MaterialTheme.typography.labelLarge.copy(color = Color.White)
//                )
//            }
//        }
//    }
//}
