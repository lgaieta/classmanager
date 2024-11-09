package com.lgaieta.classmanager.students.ui.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.R
import com.lgaieta.classmanager.subjects.models.Subject

@Composable
fun StudentDetailsSubjects(subjects: List<Subject>) {
    Column {
        Text(
            text = stringResource(R.string.subjects),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (subjects.isEmpty()) {
            Text(
                text = stringResource(R.string.no_subjects),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        if (subjects.isNotEmpty()) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(subjects) { subject ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
                        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.surfaceContainerHigh)
                    ) {
                        Text(
                            text = subject.name,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .padding(24.dp)
                        )
                    }
                }
            }
        }
    }
}