package com.lgaieta.classmanager.subjects.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.lgaieta.classmanager.ui.theme.BottomPagePadding
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import com.lgaieta.classmanager.ui.theme.TopPagePadding
import kotlinx.coroutines.launch

@Composable
fun SubjectDetailsScreen(
    modifier: Modifier = Modifier,
    subjectDetailsViewModel: SubjectDetailsViewModel,
    bottomNavBarActions: BottomNavBarActions
) {
    val subjectDetailsState by subjectDetailsViewModel.subjectDetailsState.collectAsState()
    val tasksState by subjectDetailsViewModel.tasksState.collectAsState()
    val studentsState by subjectDetailsViewModel.studentsState.collectAsState()
    val isNotFound = subjectDetailsState.subject == null
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        bottomBar = { BottomNavBar(actions = bottomNavBarActions) }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .padding(
                    start = HorizontalPagePadding,
                    end = HorizontalPagePadding,
                )
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            item {
                Spacer(modifier = Modifier.height(TopPagePadding))
            }
            item {
                if (isNotFound) {
                    SubjectDetailsNotFound()
                }
            }
            if (!isNotFound) {
                item {
                    SubjectDetailsHeader(title = subjectDetailsState.subject!!.name)
                    Spacer(modifier = Modifier.height(40.dp))
                }
                item {
                    SubjectDetailsButtons(
                        onEdit = { subjectDetailsViewModel.onEdit() },
                        onDelete = { coroutineScope.launch { subjectDetailsViewModel.onDelete() } }
                    )
                }
                subjectDetailsState.subject!!.info?.let {
                    item {
                        Spacer(modifier = Modifier.height(32.dp))
                        SubjectDetailsInfo(info = it)
                    }
                }
                item { Spacer(modifier = Modifier.height(32.dp)) }

                if (tasksState.isNotEmpty()) {
                    item {
                        SubjectDetailsTasks(
                            tasks = tasksState,
                            onTaskClick = { subjectDetailsViewModel.onTaskClick(it) }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                item {
                    NewTaskButton(
                        onClick = { subjectDetailsViewModel.onNewTask() },
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                }
                item{
                    Text(
                        text = stringResource(R.string.students_list_title),
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                 )
                }
                if (studentsState.isNotEmpty()) {
                    items(studentsState) { student ->
                        Card(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                            Text(
                                text = student.name,
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                } else {
                    item {
                        Text(
                            text = stringResource(R.string.students_not_found),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .padding(24.dp)
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(BottomPagePadding))
            }
        }
    }
}

@Composable
fun SubjectDetailsNotFound() {
    Column {
        Text(text = stringResource(R.string.subject_not_found))
    }
}

@Composable
fun SubjectDetailsHeader(title: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}


@Composable
fun SubjectDetailsInfo(info: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(end = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.info_label),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = info,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
