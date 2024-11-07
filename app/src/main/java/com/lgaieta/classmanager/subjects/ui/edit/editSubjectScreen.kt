package com.lgaieta.classmanager.subjects.ui.edit


import EditSubjectViewModel
import androidx.compose.foundation.layout.*
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
import com.lgaieta.classmanager.ui.theme.TopPagePadding
import kotlinx.coroutines.launch


@Composable
fun EditSubjectScreen(
    modifier: Modifier = Modifier,
    editSubjectViewModel: EditSubjectViewModel,
) {
    val uiState by editSubjectViewModel.editSubjectState.collectAsState()
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
            EditSubjectHeader()
            Spacer(modifier = Modifier.height(48.dp))
            EditSubjectForm(
                nameValue = uiState.name,
                onNameChange = { editSubjectViewModel.changeName(it) },
                infoValue = uiState.info,
                onInfoChange = { editSubjectViewModel.changeInfo(it) },
                onSubmit = {
                    coroutineScope.launch {
                        editSubjectViewModel.editSubject()
                    }
                },
            )
        }
    }
}

@Composable
fun EditSubjectHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.edit_subject),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}




