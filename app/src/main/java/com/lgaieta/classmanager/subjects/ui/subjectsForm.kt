package com.lgaieta.classmanager.subjects.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SubjectForm(
    onSubmit: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        NewSubjectButton(onClick = onSubmit)
    }
}


@Composable
 fun SubjectItem(subject: Subject) {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
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
                    .padding(8.dp)
            ) {
                Text(text = subject.firstTime, style = MaterialTheme.typography.labelLarge)
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Box(
                modifier = Modifier
                    .padding(8.dp)
            ) {

                Text(text = subject.secondTime, style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}


@Composable
fun NewSubjectButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter)
    {
       
    IconButton(onClick =onClick ,
        modifier = Modifier
            .padding(16.dp),
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar Materia", tint = Color.White)
    }
   }
}


// Prueba