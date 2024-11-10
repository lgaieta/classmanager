package com.lgaieta.classmanager.subjects.ui.new

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.R
import com.lgaieta.classmanager.ui.BottomNavBar
import com.lgaieta.classmanager.ui.BottomNavBarActions
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import com.lgaieta.classmanager.ui.theme.TopPagePadding
import kotlinx.coroutines.launch

@Composable
fun NewSubjectScreen(
    modifier: Modifier = Modifier,
    newSubjectViewModel: NewSubjectViewModel,
    bottomNavBarActions: BottomNavBarActions,
) {
    val uiState by newSubjectViewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold (
        bottomBar = {
            BottomNavBar(actions = bottomNavBarActions)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(
                    start = HorizontalPagePadding,
                    end = HorizontalPagePadding,
                    bottom = innerPadding.calculateBottomPadding()
                )
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(TopPagePadding + innerPadding.calculateTopPadding()))
            NewSubjectHeader()
            Spacer(modifier = Modifier.height(48.dp))
            NewSubjectForm(
                nameValue = uiState.name,
                nameError = uiState.nameError,
                onNameChange = { newSubjectViewModel.changeName(it) },
                infoValue = uiState.info,
                onInfoChange = { newSubjectViewModel.changeInfo(it) },
                onSubmit = {
                    coroutineScope.launch {
                        newSubjectViewModel.saveSubject()
                    }
                },
                onCancel = { newSubjectViewModel.cancel() }
            )
        }
    }
}

@Composable
fun NewSubjectHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.new_subject),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}