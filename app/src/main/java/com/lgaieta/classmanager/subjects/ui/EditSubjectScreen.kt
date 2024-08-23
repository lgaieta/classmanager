package com.lgaieta.classmanager.subjects.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import androidx.compose.runtime.*
import androidx.compose.foundation.border

import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lgaieta.classmanager.R
import com.lgaieta.classmanager.ui.theme.TopPagePadding


@Composable
fun EditSubject(
    modifier: Modifier = Modifier,
    EditSubjectViewModel: EditSubjectViewModel = viewModel(),

    onEdit: () -> Unit = {}
) {
    val uiState by EditSubjectViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .padding(
                start = HorizontalPagePadding,
                end = HorizontalPagePadding,
                top = TopPagePadding
            )
            .fillMaxWidth()
    ) {
        EditSubjectHeader()
        Spacer(modifier = Modifier.height(48.dp))
        EditSubjectForm(
            nameValue = uiState.name,
            onNameChange = { EditSubjectViewModel.changeName(it) },
            courseValue= uiState.course,
            onCourseChange= { EditSubjectViewModel.changeCourse(it) },
            onSubmit= {},
            onCancel={}
        )
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




