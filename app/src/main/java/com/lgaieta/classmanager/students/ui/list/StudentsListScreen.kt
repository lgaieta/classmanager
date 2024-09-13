package com.lgaieta.classmanager.students.ui.list

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.R
import com.lgaieta.classmanager.ui.BottomNavBar
import com.lgaieta.classmanager.ui.BottomNavBarActions
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import com.lgaieta.classmanager.ui.theme.TopPagePadding

@Composable
fun StudentsListScreen(
    bottomNavBarActions: BottomNavBarActions,
    studentsListViewModel: StudentsListViewModel,
    modifier: Modifier = Modifier
) {
    val listState by studentsListViewModel.studentsListState.collectAsState()

    Scaffold(
        bottomBar = {
            BottomNavBar(actions = bottomNavBarActions)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(
                start = HorizontalPagePadding,
                end = HorizontalPagePadding,
                top = TopPagePadding + innerPadding.calculateTopPadding()
            )
        ) {
            StudentHeader()
            Spacer(modifier = Modifier.height(48.dp))
            if (listState.students.isEmpty()) {
                Text(
                    text = stringResource(R.string.empty_students_list),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}


@Composable
private fun StudentHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.students_list_title),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}