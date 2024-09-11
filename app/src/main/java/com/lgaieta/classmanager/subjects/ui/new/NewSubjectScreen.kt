package com.lgaieta.classmanager.subjects.ui.new

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import com.lgaieta.classmanager.ui.theme.TopPagePadding
import kotlinx.coroutines.launch

@Composable
fun NewSubjectScreen(
    modifier: Modifier = Modifier,
    newSubjectViewModel: NewSubjectViewModel,
    onNewTime: () -> Unit,
    afterCreate: () -> Unit = {}
) {
    val uiState by newSubjectViewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .padding(
                    start = HorizontalPagePadding,
                    end = HorizontalPagePadding,
                    top = TopPagePadding + innerPadding.calculateTopPadding(),
                )
                .fillMaxWidth()
        ) {
            NewSubjectHeader()
            Spacer(modifier = Modifier.height(48.dp))
            NewSubjectForm(
                nameValue = uiState.name,
                onNameChange = { newSubjectViewModel.changeName(it) },
                courseValue = uiState.course,
                onCourseChange = { newSubjectViewModel.changeCourse(it) },
                onNewTime = onNewTime,
                onSubmit = {
                    coroutineScope.launch {
                        newSubjectViewModel.saveSubject()
                    }
                    afterCreate()
                }
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