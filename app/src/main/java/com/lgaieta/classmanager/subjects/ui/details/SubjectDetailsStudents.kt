package com.lgaieta.classmanager.subjects.ui.details

import com.lgaieta.classmanager.students.models.Student
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.R


@Composable
fun SubjectDetailsStudents(students: List<Student>){
    Column{
        Text(
            text = stringResource(R.string.students_list_title),
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(8.dp))
        if(students.isNotEmpty()){
            LazyColumn (modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ){
                items(students){ student ->
                    Card(modifier = Modifier.fillMaxWidth()){
                        Text(
                            text = student.name,
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
        else{
            Text(
                text = stringResource(R.string.students_not_found),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(24.dp)
            )
        }


    }
}
