package com.lgaieta.classmanager.students.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            Text(text = "Estudiantes")
        }
    }
}