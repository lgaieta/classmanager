package com.lgaieta.classmanager.tasks.ui.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.tasks.models.Task

@Composable
fun TaskItem(task: Task, modifier: Modifier = Modifier, onClick: (id: Int) -> Unit = {},
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        ),
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceContainerHigh),
        onClick = { onClick(task.id) }
    ) {
        Text(
            text = task.name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 32.dp , start = 32.dp, end = 32.dp)
        )
    }
}
