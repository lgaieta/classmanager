package com.lgaieta.classmanager.tasks.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.lgaieta.classmanager.R
import com.lgaieta.classmanager.ui.BottomNavBar
import com.lgaieta.classmanager.ui.BottomNavBarActions
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import com.lgaieta.classmanager.ui.theme.TopPagePadding
import kotlinx.coroutines.launch

@Composable
fun TaskDetailsScreen(
    taskDetailsViewModel: TaskDetailsViewModel,
    bottomNavBarActions: BottomNavBarActions,
    modifier: Modifier = Modifier,
) {
    val taskDetailsState by taskDetailsViewModel.taskDetailsState.collectAsState()
    val subjectState by taskDetailsViewModel.subjectState.collectAsState()
    val studentsState by taskDetailsViewModel.studentsState.collectAsState()
    val isNotFound = taskDetailsState.task == null
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        bottomBar = { BottomNavBar(bottomNavBarActions) }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .padding(
                    start = HorizontalPagePadding,
                    end = HorizontalPagePadding,
                )
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item{
            Spacer(modifier = Modifier.height(TopPagePadding ))
            }

            if (!isNotFound) {
                item{
                    if (subjectState != null) {
                        SubjectBadge(name = subjectState!!.name)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item{
                    TaskDetailsHeader(title = taskDetailsState.task!!.name)
                    Spacer(modifier = Modifier.height(36.dp))
                }
                item{
                    TaskDetailsButtons(
                        onEdit = { taskDetailsViewModel.onEdit() },
                        onDelete = { coroutineScope.launch { taskDetailsViewModel.onDelete() } },
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }

                if (taskDetailsState.task!!.description.isNotEmpty()) {
                 item{
                     TaskDescription(description = taskDetailsState.task!!.description)
                    }
                }
                item{
                Spacer(modifier = Modifier.height(24.dp))
                }
                item{
                        Text(
                            text = stringResource(R.string.notes),
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                           // modifier = Modifier.align(Alignment.Start)
                        )
                        TaskDetailsStudents(students = studentsState)
                }
            }
            else {
                item{
                    TaskDetailsNotFound()
                }
            }
        }
    }
}

@Composable
private fun TaskDescription(description: String) {
    Column {
        Text(
            text = stringResource(R.string.description),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Composable
fun TaskDetailsNotFound() {
        Text(text = stringResource(R.string.task_not_found),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(24.dp)
        )
}

@Composable
private fun SubjectBadge(name: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 8.dp, vertical = 2.dp)
        )
    }
}

@Composable
fun TaskDetailsHeader(title: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}
