package com.lgaieta.classmanager.tasks.ui.details

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.R

@Composable
fun TaskDetailsButtons(onEdit: () -> Unit, onDelete: () -> Unit, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        FloatingActionButton(
            onClick = onEdit,
            elevation = FloatingActionButtonDefaults.elevation(0.dp),
            modifier = Modifier.weight(1f)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.edit_task_fab_description),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = stringResource(R.string.edit_task))
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        TaskDetailsDeleteButton(
            onDelete = onDelete,
            modifier = Modifier.weight(1f)
        )
    }
}
