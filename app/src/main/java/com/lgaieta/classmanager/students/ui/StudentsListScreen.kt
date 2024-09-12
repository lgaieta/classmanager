package com.lgaieta.classmanager.students.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.lgaieta.classmanager.R
import com.lgaieta.classmanager.ui.BottomNavBar
import com.lgaieta.classmanager.ui.BottomNavBarActions
import com.lgaieta.classmanager.ui.theme.HorizontalPagePadding
import com.lgaieta.classmanager.ui.theme.TopPagePadding

@Composable
fun StudentsListScreen(bottomNavBarActions: BottomNavBarActions, modifier: Modifier = Modifier) {
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