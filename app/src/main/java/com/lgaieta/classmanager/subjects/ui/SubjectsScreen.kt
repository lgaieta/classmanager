package com.lgaieta.classmanager.subjects.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.lgaieta.classmanager.R
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview as Preview1


// Data class for example
data class Subject(val name: String, val details: String, val firstTime: String, val secondTime: String)


@Composable
fun SubjectsScreen(
    modifier: Modifier = Modifier,
    SubjectViewModel: SubjectViewModel

) {    val uiState by SubjectViewModel.uiState.collectAsState()

    var subjects by remember { mutableStateOf(
        listOf(
            Subject("Modelo y sistemas", "7mo 2da E.E.S.T. Nª1", "Lunes 10:00", "Jueves 14:00")
        )
    ) }
    SubjectHeader()
    Spacer(modifier = Modifier.height(48.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(subjects.size) { index ->
                SubjectItem(subjects[index])
            }
        }

    Spacer(modifier = Modifier.height(48.dp))
    NewSubjectButton(onClick={})
}


@Composable
fun SubjectHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.new_subject),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}


@Composable
fun NewSubjectButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    )
    {

        IconButton(
            onClick = onClick,
            modifier = Modifier
                .padding(16.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Agregar Materia",
                tint = Color.White
            )
        }
    }
}
// Prueba
