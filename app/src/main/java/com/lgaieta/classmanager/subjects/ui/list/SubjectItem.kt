package com.lgaieta.classmanager.subjects.ui.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.subjects.models.Subject

private val ItemPadding = 32.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectItem(subject: Subject, modifier: Modifier = Modifier, onClick: (id: Int) -> Unit = {}) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
        ),
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primaryContainer.copy(alpha = 1f)),
        onClick = { onClick(subject.id) }
    ) {
        Text(
            text = subject.name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = ItemPadding, start = ItemPadding, end = ItemPadding)
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (subject.info != null) {
            Text(
                text = subject.info,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = ItemPadding, end = ItemPadding, bottom = ItemPadding)
            )
        }
    }
}