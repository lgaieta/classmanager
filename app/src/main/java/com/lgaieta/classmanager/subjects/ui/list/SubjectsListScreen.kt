package com.lgaieta.classmanager.subjects.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.lgaieta.classmanager.R
import androidx.compose.ui.graphics.Color
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import com.lgaieta.classmanager.ui.theme.TopPagePadding

@Composable
fun SubjectsListScreen(
    modifier: Modifier = Modifier,
    subjectsListViewModel: SubjectsListViewModel,
    onNewSubjectClick: () -> Unit = {}
) {
    val listState by subjectsListViewModel.subjectListState.collectAsState()
    Scaffold(
        floatingActionButton = {
            NewSubjectButton(
                onClick = onNewSubjectClick,
                modifier = Modifier.fillMaxSize()
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    )
    { innerPadding ->
        Column(
            modifier = modifier.padding(
                horizontal = HorizontalPagePadding,
                vertical = TopPagePadding + innerPadding.calculateTopPadding()
            )
        ) {
            SubjectHeader()
            Spacer(modifier = Modifier.height(48.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(listState.subjects) { subject ->
                    SubjectItem(subject)
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
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
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun NewSubjectButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    FloatingActionButton(onClick = onClick) {
        Icon(Icons.Filled.Add, stringResource(R.string.new_subject_fab_description))
    }
}
