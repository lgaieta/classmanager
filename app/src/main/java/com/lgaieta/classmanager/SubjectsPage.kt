package com.lgaieta.classmanager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.ui.theme.Background200
import com.lgaieta.classmanager.ui.theme.Background100



data class Subject(val name: String, val details: String, val firstTime: String, val secondTime: String)

@Composable
fun SubjectsPage(modifier: Modifier = Modifier) {
    var subjects by remember { mutableStateOf(
        listOf(
            Subject("Modelos y sistemas", "7mo 2da E.E.S.T. Nª1", "Lunes 10:00", "Jueves 14:00")
        )
    ) }

    Column(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Materias",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
        )
    }

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 72.dp)
        ) {
            items(subjects.size) { index ->
                SubjectItem(subjects[index])
            }
        }

        IconButton(
            onClick = {
                subjects = subjects + Subject("Nueva Materia", "Curso al que pertenece", "09:00", "11:00")
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.extraLarge)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar Materia", tint = Color.White)
        }
    }
}

@Composable
fun SubjectItem(subject: Subject) {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .background(Background200, shape = RoundedCornerShape(16.dp))
            .padding(20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = subject.name,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )

        Text(
                text = subject.details,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Medium)
        )

        Row(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .background(Background100 , shape = RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                Text(text = subject.firstTime, style = MaterialTheme.typography.labelLarge)
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Box(
                modifier = Modifier
                    .background(Background100, shape = RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {

                Text(text = subject.secondTime, style = MaterialTheme.typography.labelLarge)
            }
            }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewContent() {
    SubjectsPage()
}
