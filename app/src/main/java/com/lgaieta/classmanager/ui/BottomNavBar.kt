package com.lgaieta.classmanager.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.lgaieta.classmanager.R

@Composable
fun BottomNavBar(actions: BottomNavBarActions) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        actions.onSubjectsClick()
                    }
                    .fillMaxHeight()
            ) {
                Icon(
                    painter = painterResource(R.drawable.school_filled_24),
                    contentDescription = stringResource(R.string.subject_list_nav_item_label)
                )
                Text(text = stringResource(R.string.subject_list_nav_item_label), style = MaterialTheme.typography.bodySmall)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        actions.onStudentsClick()
                    }
                    .fillMaxHeight()
            ) {
                Icon(
                    painter = painterResource(R.drawable.group_filled_24),
                    contentDescription = stringResource(R.string.students_list_nav_item_label)
                )
                Text(text = stringResource(R.string.students_list_nav_item_label), style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

data class BottomNavBarActions(
    val onHomeClick: () -> Unit,
    val onSubjectsClick: () -> Unit,
    val onStudentsClick: () -> Unit
)