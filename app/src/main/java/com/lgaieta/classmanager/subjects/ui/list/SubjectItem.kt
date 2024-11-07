package com.lgaieta.classmanager.subjects.ui.list

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.subjects.models.Subject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectItem(subject: Subject, modifier: Modifier = Modifier, onClick: (id: Int) -> Unit = {}) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        onClick = { onClick(subject.id) }
    ) {
        Text(
            text = subject.name,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 24.dp, end = 24.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (subject.info != null) {
            Text(
                text = subject.info,
                style = MaterialTheme.typography.bodyMedium,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
            )
        }
    }
}