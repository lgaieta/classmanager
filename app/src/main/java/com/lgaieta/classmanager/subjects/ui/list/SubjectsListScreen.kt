package com.lgaieta.classmanager.subjects.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.R
import com.lgaieta.classmanager.ui.BottomNavBar
import com.lgaieta.classmanager.ui.BottomNavBarActions
import com.lgaieta.classmanager.ui.theme.BottomPagePadding
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import com.lgaieta.classmanager.ui.theme.TopPagePadding

@Composable
fun SubjectsListScreen(
    modifier: Modifier = Modifier,
    subjectsListViewModel: SubjectsListViewModel,
    onNewSubjectClick: () -> Unit = {},
    onSubjectClick: (id: Int) -> Unit = {},
    bottomNavBarActions: BottomNavBarActions
) {
    val listState by subjectsListViewModel.subjectListState.collectAsState()
    Scaffold(
        floatingActionButton = {
            NewSubjectButton(
                onClick = onNewSubjectClick,
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomNavBar(actions = bottomNavBarActions)
        }
    )
    { innerPadding ->
        LazyColumn(
            modifier = modifier.padding(
                start = HorizontalPagePadding,
                end = HorizontalPagePadding,
            )
        ) {
            item {
                Spacer(modifier = Modifier.height(TopPagePadding + innerPadding.calculateTopPadding()))
                SubjectHeader()
                Spacer(modifier = Modifier.height(48.dp))
            }
            if (listState.isEmpty()) {
                item {
                    Text(
                        text = stringResource(R.string.empty_subjects_list),
                        textAlign = TextAlign.Center,
                    )
                }
            }
            items(listState) { subject ->
                SubjectItem(subject, onClick = onSubjectClick)
                Spacer(modifier = Modifier.height(12.dp))
            }
            item {
                Spacer(modifier = Modifier.height(BottomPagePadding + innerPadding.calculateBottomPadding()))
            }
        }
    }
}

@Composable
fun SubjectHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.subjects_list_title),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
        )
    }
}

@Composable
fun NewSubjectButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Icon(Icons.Filled.Add, stringResource(R.string.new_subject_fab_description))
    }
}
