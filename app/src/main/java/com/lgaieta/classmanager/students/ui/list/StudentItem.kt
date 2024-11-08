package com.lgaieta.classmanager.students.ui.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.lgaieta.classmanager.students.models.Student

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentItem(student: Student, modifier: Modifier = Modifier, onClick: (id: Long) -> Unit = {}) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        ),
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceContainerHighest),
        onClick = { onClick(student.id) }
    ) {
        Text(
            text = student.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
            modifier = modifier
                .padding(horizontal = 40.dp, vertical = 64.dp)
                .widthIn(max = 175.dp)
                .fillMaxWidth()
        )
    }
}