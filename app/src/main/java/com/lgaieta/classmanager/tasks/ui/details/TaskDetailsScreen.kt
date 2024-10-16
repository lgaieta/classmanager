package com.lgaieta.classmanager.tasks.ui.details

import androidx.compose.foundation.layout.*
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
    val isNotFound = taskDetailsState.task == null
    val coroutineScope = rememberCoroutineScope()

    Scaffold(bottomBar = { BottomNavBar(bottomNavBarActions) }) { innerPadding ->
        Column(
            modifier = modifier
                .padding(
                    start = HorizontalPagePadding,
                    end = HorizontalPagePadding,
                    top = TopPagePadding + innerPadding.calculateTopPadding()
                )
                .fillMaxWidth()
        ) {
            if (isNotFound) TaskDetailsNotFound()
            if (!isNotFound) {
                TaskDetailsHeader(title = taskDetailsState.task!!.name)
                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (subjectState != null)
                        Column(
                            modifier = modifier
                                .padding(end = 16.dp)
                                .weight(1f)
                        ) {
                            Text(
                                text = stringResource(R.string.subject),
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.align(Alignment.Start)
                            )
                            Text(
                                text = subjectState!!.name,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.align(Alignment.Start)
                            )
                        }
                    TaskDetailsButtons(
                        onEdit = { taskDetailsViewModel.onEdit() },
                        onDelete = { coroutineScope.launch { taskDetailsViewModel.onDelete() } },
                        modifier = Modifier.weight(1f)
                    )

                }
                Spacer(modifier = Modifier.height(40.dp))
                Column(
                    modifier = modifier
                        .padding(end = 16.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.description),
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    if (!taskDetailsState.task!!.description.isNullOrEmpty()) {
                        Card {
                            Text(
                                text = taskDetailsState.task!!.description,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .padding(24.dp)
                                    .widthIn(max = 150.dp)
                            )
                        }
                    } else {
                        Text(
                            text = stringResource(R.string.description_not_found),
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(24.dp)
                        )
                    }


                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = modifier
                    .padding(end = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.notes),
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.align(Alignment.Start)
                )
                Text(
                    text = "La sección de notas de tareas se encuentra en desarrollo.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
        }
    }
}

@Composable
fun TaskDetailsNotFound() {
    Column {
        Text(text = stringResource(R.string.task_not_found))
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
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}