package com.lgaieta.classmanager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.lgaieta.classmanager.ui.theme.Background200
import com.lgaieta.classmanager.ui.theme.Background100
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding


@Composable
fun SubjectDetailPage(subject: Subject, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding(HorizontalPagePadding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = subject.name,
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.CenterHorizontally) .padding(top = 40.dp)
        )

        // Curso
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp) // Espacio entre la columna y los botones
            ) {
                Text(
                    text = "Curso",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.align(Alignment.Start)
                )
                Text(
                    text = subject.details,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                )
            }

            IconButton(
                onClick = { /* Editar curso */ },
                modifier = Modifier
                    .background(Background200) // Usa el color que prefieras
                    .size(48.dp)
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar", tint = MaterialTheme.colorScheme.primary)
            }

            Spacer(modifier = Modifier.width(16.dp))

            IconButton(
                onClick = { /* Eliminar curso */ },
                modifier = Modifier
                    .background(Background200)
                    .size(48.dp)
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar", tint = MaterialTheme.colorScheme.primary)
            }
}
        // Horarios
        Text(
            text = "Horarios",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.Start).padding(top = 16.dp)
        )
        // Lista de horarios
        Column(modifier = Modifier.fillMaxWidth()) {
            repeat(3) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .background(Background100, shape = RoundedCornerShape(8.dp))
                        .padding(8.dp)
                ) {
                    Text(
                        text = "${subject.firstTime} - ${subject.secondTime}",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }


        // Exámenes y trabajos prácticos
        Text(
            text = "Exámenes y trabajos prácticos",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.Start).padding(top = 16.dp)
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            // Ejemplo de tarea
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Background100, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Evaluación",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Presentación sobre los distintos modelos de autos",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            // Botón para agregar nueva tarea
            Button(
                onClick = { /* Agregar nueva tarea */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .padding(8.dp)


            ) {
                Text(text = "+ Nueva tarea")
            }
        }

        // Alumnos
        Text(
            text = "Alumnos",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.Start).padding(top = 16.dp)
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            // Ejemplo de alumno
            repeat(3) { // Reemplaza con el número real de alumnos
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .background(Background100, shape = RoundedCornerShape(8.dp))
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Aieta Luciano",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Botón para agregar nuevo alumno
            Button(
                onClick = { /* Agregar nuevo alumno */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .padding(8.dp)
            ) {
                Text(text = "+ Nuevo alumno")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewSubjectDetailPage() {
    SubjectDetailPage(
        subject = Subject(
            name = "Modelos y sistemas",
            details = "7mo 2da E.E.S.T. Nª1",
            firstTime = "13:00",
            secondTime = "15:00"
        )
    )
}
