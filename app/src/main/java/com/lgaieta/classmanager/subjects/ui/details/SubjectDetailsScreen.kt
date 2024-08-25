package com.lgaieta.classmanager.subjects.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.lgaieta.classmanager.R
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import com.lgaieta.classmanager.ui.theme.TopPagePadding

@Composable
fun SubjectDetailsScreen(
    subjectDetailsViewModel: SubjectDetailsViewModel,
    modifier: Modifier = Modifier,
) {
    val subjectDetailsState by subjectDetailsViewModel.subjectDetailsState.collectAsState()
    Column(
        modifier = Modifier
            .padding(
                start = HorizontalPagePadding,
                end = HorizontalPagePadding,
                top = TopPagePadding
            )
            .fillMaxWidth()
    ) {
        if (subjectDetailsState.subject == null) Text(text = "La materia que estás buscando no existe.")
        else Text(text = subjectDetailsState.subject!!.name)
    }
}


@Composable
fun DetailedSubjectHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Subject name",
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}


@Composable
fun DetailsCourse() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 64.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.course_subject),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = "course details",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
            )
        }
        IconButton(
            onClick = { },
            modifier = Modifier
                .size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Editar",
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        IconButton(
            onClick = {},
            modifier = Modifier
                .size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Eliminar",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun DetailsTime() {
    Text(
        text = stringResource(R.string.time_label),
        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier
            .padding(top = 16.dp)
    )
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .padding(8.dp)
        ) {
            Text(
                text = "10:00",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun DetailTasks() {
    Text(
        text = stringResource(R.string.tasks_label),
        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier
            .padding(top = 16.dp)
    )
    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .padding(16.dp)
        ) {
            Column {
                TaskState()
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Presentación sobre los distintos modelos de autos",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
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
}


@Composable
fun TaskState() {
    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = "Evaluación",
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun StudentsList() {
    Text(
        text = "Alumnos",
        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier
            .padding(top = 16.dp)
    )
    Column(modifier = Modifier.fillMaxWidth()) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .padding(8.dp)
        ) {
            Text(
                text = "Aieta Luciano",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
        }
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .padding(8.dp)
        ) {
            Text(text = "+ Nuevo alumno")
        }
    }
}