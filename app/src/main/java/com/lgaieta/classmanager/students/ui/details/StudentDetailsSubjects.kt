package com.lgaieta.classmanager.students.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
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
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (subjects.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(subjects) { subject ->
                    Card {
                        Text(
                            text = subject.name,
                            modifier = Modifier
                                .padding(24.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}