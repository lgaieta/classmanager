package com.lgaieta.classmanager.subjects.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.R
import com.lgaieta.classmanager.tasks.models.Task

@Composable
fun SubjectDetailsTasks(tasks: List<Task> = emptyList(), modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.tasks),
        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
        modifier = modifier
    )
    Spacer(modifier = Modifier.height(8.dp))
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(tasks) { task ->
            Card {
                Text(
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    text = task.name,
                    modifier = Modifier
                        .padding(24.dp)
                        .widthIn(max = 150.dp)
                )
            }
        }
    }
}