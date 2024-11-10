package com.lgaieta.classmanager.tasks.ui.edit


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import androidx.compose.runtime.*

import androidx.compose.ui.res.stringResource
import com.lgaieta.classmanager.R
import com.lgaieta.classmanager.ui.BottomNavBar
import com.lgaieta.classmanager.ui.BottomNavBarActions
import com.lgaieta.classmanager.ui.theme.BottomPagePadding
import com.lgaieta.classmanager.ui.theme.TopPagePadding
import kotlinx.coroutines.launch


@Composable
fun EditTaskScreen(
    modifier: Modifier = Modifier,
    editTaskViewModel: EditTaskViewModel,
    bottomNavBarActions: BottomNavBarActions
) {
    val uiState by editTaskViewModel.editTaskState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(bottomBar = { BottomNavBar(actions = bottomNavBarActions) })
    { innerPadding ->
        Column(
            modifier = modifier
                .padding(
                    start = HorizontalPagePadding,
                    end = HorizontalPagePadding,
                )
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(TopPagePadding + innerPadding.calculateTopPadding()))
            EditTaskHeader()
            Spacer(modifier = Modifier.height(48.dp))
            EditTaskForm(
                nameValue = uiState.name,
                nameError = uiState.nameError,
                onNameChange = { editTaskViewModel.changeName(it) },
                descValue = uiState.description,
                onDescChange = { editTaskViewModel.changeDescription(it) },
                onSubmit = {
                    coroutineScope.launch {
                        editTaskViewModel.editTask()
                    }
                },
                onCancel = {
                    editTaskViewModel.cancel()
                }
            )
            Spacer(modifier = Modifier.height(BottomPagePadding + innerPadding.calculateBottomPadding()))
        }
    }
}

@Composable
fun EditTaskHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.edit_task),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}




