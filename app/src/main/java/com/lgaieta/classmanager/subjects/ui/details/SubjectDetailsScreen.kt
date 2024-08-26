package com.lgaieta.classmanager.subjects.ui.details

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import com.lgaieta.classmanager.R
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import com.lgaieta.classmanager.ui.theme.TopPagePadding
import kotlinx.coroutines.launch

@Composable
fun SubjectDetailsScreen(
    subjectDetailsViewModel: SubjectDetailsViewModel,
    modifier: Modifier = Modifier,
) {
    val subjectDetailsState by subjectDetailsViewModel.subjectDetailsState.collectAsState()
    val isNotFound = subjectDetailsState.subject == null
    val coroutineScope = rememberCoroutineScope()

    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .padding(
                    start = HorizontalPagePadding,
                    end = HorizontalPagePadding,
                    top = TopPagePadding + innerPadding.calculateTopPadding()
                )
                .fillMaxWidth()
        ) {
            if (isNotFound) SubjectDetailsNotFound()
            if (!isNotFound) {
                SubjectDetailsHeader(title = subjectDetailsState.subject!!.name)
                Spacer(modifier = Modifier.height(40.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SubjectDetailsCourse(
                        course = subjectDetailsState.subject!!.course,
                        modifier = Modifier.weight(1f)
                    )
                    SubjectDetailsButtons(
                        onEdit = { subjectDetailsViewModel.onEdit() },
                        onDelete = { coroutineScope.launch { subjectDetailsViewModel.onDelete() } }
                    )
                }
            }
        }
    }
}

@Composable
fun SubjectDetailsNotFound() {
    Column {
        Text(text = stringResource(R.string.subject_not_found))
    }
}

@Composable
fun SubjectDetailsHeader(title: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}


@Composable
fun SubjectDetailsCourse(course: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(end = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.course_subject),
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = course,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}