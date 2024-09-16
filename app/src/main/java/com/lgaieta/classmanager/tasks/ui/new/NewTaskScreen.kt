package com.lgaieta.classmanager.tasks.ui.new


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.R
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import com.lgaieta.classmanager.ui.theme.TopPagePadding
import kotlinx.coroutines.launch

@Composable
fun NewTaskScreen(
    modifier: Modifier = Modifier,
    newTaskViewModel: NewTaskViewModel,
    afterCreate: () -> Unit = {}
) {
    val uiState by newTaskViewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .padding(
                start = HorizontalPagePadding,
                end = HorizontalPagePadding,
                top = TopPagePadding
            )
            .fillMaxWidth()
    ) {
        NewTaskHeader()
        Spacer(modifier = Modifier.height(48.dp))
        NewTaskForm(
            nameValue = uiState.name,
            onNameChange = { newTaskViewModel.changeName(it) },
            onSubmit = {
                coroutineScope.launch {
                    newTaskViewModel.saveTask()
                }
                afterCreate()
            }
        )
    }
}

@Composable
fun NewTaskHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.new_task),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}