package com.lgaieta.classmanager.students.ui.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
fun EditStudentScreen(
    editStudentViewModel: EditStudentViewModel,
    modifier: Modifier = Modifier,
    bottomNavBarActions: BottomNavBarActions
) {
    val uiState by editStudentViewModel.editStudentState.collectAsState()
    val availableSubjects by editStudentViewModel.availableSubjects.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        bottomBar = { BottomNavBar(actions = bottomNavBarActions) }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .padding(
                    start = HorizontalPagePadding,
                    end = HorizontalPagePadding,
                    top = TopPagePadding + innerPadding.calculateTopPadding(),
                )
                .fillMaxWidth()
        ) {
            item {
                EditStudentHeader()
                Spacer(modifier = Modifier.height(48.dp))
            }
            item {
                EditStudentForm(
                    nameValue = uiState.name,
                    nameError = uiState.nameError,
                    onNameChange = { editStudentViewModel.changeName(it) },
                    onSubmit = {
                        coroutineScope.launch {
                            editStudentViewModel.editStudent()
                        }
                    },
                    onSelectedSubjectsChange = { editStudentViewModel.onSelectedSubjectsChange(it) },
                    selectedSubjects = uiState.selectedSubjects,
                    availableSubjects = availableSubjects,
                    onSubjectsToBeDeletedChange = {
                        editStudentViewModel.onSubjectsToBeDeletedChange(
                            it
                        )
                    },
                    subjectsToBeDeleted = uiState.subjectsToBeDeleted,
                    onCancel = { editStudentViewModel.cancel() }
                )
            }
            item {
                Spacer(modifier = Modifier.height(BottomPagePadding + innerPadding.calculateBottomPadding()))
            }
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




