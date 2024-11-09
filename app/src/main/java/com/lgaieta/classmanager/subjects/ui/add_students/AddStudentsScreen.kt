package com.lgaieta.classmanager.subjects.ui.add_students

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.R
import com.lgaieta.classmanager.students.models.Student
import com.lgaieta.classmanager.ui.BottomNavBar
import com.lgaieta.classmanager.ui.BottomNavBarActions
import com.lgaieta.classmanager.ui.theme.BottomPagePadding
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import com.lgaieta.classmanager.ui.theme.TopPagePadding
import kotlinx.coroutines.launch

@Composable
fun AddStudentsScreen(
    bottomNavBarActions: BottomNavBarActions,
    addStudentsViewModel: AddStudentsViewModel,
    modifier: Modifier = Modifier,
) {
    val listState by addStudentsViewModel.studentsListState.collectAsState()
    val selectedStudentsIds by addStudentsViewModel.selectedStudentsIds.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            Column {
                Box(modifier = Modifier.padding(16.dp)) {
                    Button(
                        onClick = { coroutineScope.launch { addStudentsViewModel.onSubmit() } },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(
                            text = stringResource(id = R.string.add_students),
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
                BottomNavBar(actions = bottomNavBarActions)
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .padding(
                    start = HorizontalPagePadding,
                    end = HorizontalPagePadding,
                )
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(TopPagePadding + innerPadding.calculateTopPadding()))
                StudentHeader()
                Spacer(modifier = Modifier.height(48.dp))
                if (listState.students.isEmpty()) {
                    Text(
                        text = stringResource(R.string.empty_students_selector_list),
                        textAlign = TextAlign.Center,
                    )
                }
            }
            items(listState.students) { student ->
                StudentItem(
                    student,
                    onClick = { addStudentsViewModel.onStudentClick(it) },
                    isSelected = student.id in selectedStudentsIds
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Spacer(modifier = Modifier.height(BottomPagePadding + innerPadding.calculateBottomPadding()))
            }
        }
    }
}


@Composable
private fun StudentHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.select_student_list_title),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
private fun StudentItem(
    student: Student,
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick: (id: Long) -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.surfaceContainerHigh else MaterialTheme.colorScheme.surfaceContainerLow,
        ),
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceContainerHighest),
        onClick = { onClick(student.id) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.padding(horizontal = 24.dp, vertical = 24.dp)
        ) {
            Text(
                text = student.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            if (isSelected) Icon(
                Icons.Filled.Done,
                contentDescription = stringResource(R.string.selected_text),
                modifier = Modifier.size(24.dp),
            )
        }
    }
}

@Composable
private fun NewStudentButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary,
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Icon(Icons.Filled.Add, stringResource(R.string.new_student_fab_description))
    }
}