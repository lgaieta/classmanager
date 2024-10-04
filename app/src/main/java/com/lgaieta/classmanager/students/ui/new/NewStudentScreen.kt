 package com.lgaieta.classmanager.students.ui.new

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.lgaieta.classmanager.ui.theme.BottomPagePadding
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import com.lgaieta.classmanager.ui.theme.TopPagePadding
import kotlinx.coroutines.launch

@Composable
fun NewStudentScreen(
    modifier: Modifier = Modifier,
    afterCreate: () -> Unit = {},
    newStudentViewModel: NewStudentViewModel,
    bottomNavBarActions: BottomNavBarActions
) {
    val uiState by newStudentViewModel.uiState.collectAsState()
    val availableSubjects by newStudentViewModel.availableSubjects.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        bottomBar = { BottomNavBar(actions = bottomNavBarActions) }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .padding(
                    start = HorizontalPagePadding,
                    end = HorizontalPagePadding,
                    top = TopPagePadding + innerPadding.calculateTopPadding()
                )
                .fillMaxWidth()
        ) {
            item {
                NewStudentHeader()
                Spacer(modifier = Modifier.height(48.dp))
            }

            item {
                NewStudentForm(
                    nameValue = uiState.name,
                    onNameChange = { newStudentViewModel.changeName(it) },
                    onSubmit = {
                        coroutineScope.launch {
                            newStudentViewModel.saveStudent()
                        }
                        afterCreate()
                    },
                    availableSubjects = availableSubjects,
                    selectedSubjects = uiState.selectedSubjects,
                    onSelectedSubjectsChange = { newList ->
                        newStudentViewModel.onSelectedSubjectsChange(newList)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(BottomPagePadding + innerPadding.calculateBottomPadding()))
            }
        }
    }
}


@Composable
fun NewStudentHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.new_student),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}