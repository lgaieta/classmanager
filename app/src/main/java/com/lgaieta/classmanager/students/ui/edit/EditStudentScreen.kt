package com.lgaieta.classmanager.students.ui.edit

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
fun EditStudentScreen(
    editStudentViewModel: EditStudentViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState by editStudentViewModel.editStudentState.collectAsState()
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
            EditStudentHeader()
            Spacer(modifier = Modifier.height(48.dp))
            EditStudentForm(
                nameValue = uiState.name,
                onNameChange = { editStudentViewModel.changeName(it) },
                onSubmit = {
                    coroutineScope.launch {
                        editStudentViewModel.editStudent()
                    }
                },
            )
        }
    }
}

@Composable
fun EditStudentHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.edit_student),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}




