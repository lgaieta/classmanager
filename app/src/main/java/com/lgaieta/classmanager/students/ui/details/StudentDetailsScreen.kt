package com.lgaieta.classmanager.students.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.lgaieta.classmanager.R
import com.lgaieta.classmanager.ui.BottomNavBar
import com.lgaieta.classmanager.ui.BottomNavBarActions
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import com.lgaieta.classmanager.ui.theme.TopPagePadding

@Composable
fun StudentDetailsScreen(
    studentDetailsViewModel: StudentDetailsViewModel,
    modifier: Modifier = Modifier,
    bottomNavBarActions: BottomNavBarActions
) {
    val studentDetailsState by studentDetailsViewModel.studentDetailsState.collectAsState()
    val isNotFound = studentDetailsState.student == null

    Scaffold(
        bottomBar = { BottomNavBar(actions = bottomNavBarActions) }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(
                    start = HorizontalPagePadding,
                    end = HorizontalPagePadding,
                    top = TopPagePadding + innerPadding.calculateTopPadding()
                )
                .fillMaxWidth()
        ) {
            if (isNotFound) StudentDetailsNotFound()
            if (!isNotFound) {
                StudentDetailsHeader(title = studentDetailsState.student!!.name)
                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StudentDetailsButtons(
                        onEdit = { studentDetailsViewModel.onEdit() },
                        onDelete = { studentDetailsViewModel.onDelete() }
                    )
                }
            }
        }
    }
}

@Composable
fun StudentDetailsNotFound() {
    Column {
        Text(text = stringResource(R.string.student_not_found))
    }
}

@Composable
fun StudentDetailsHeader(title: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}
