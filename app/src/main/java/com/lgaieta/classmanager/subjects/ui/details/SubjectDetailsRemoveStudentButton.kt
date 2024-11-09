package com.lgaieta.classmanager.subjects.ui.details

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.R

@Composable
fun SubjectDetailsRemoveStudentButton(onDelete: () -> Unit, modifier: Modifier) {
    var openDialog by rememberSaveable { mutableStateOf(false) }
    if (openDialog) ConfirmDeleteDialog(
        onConfirmation = {
            onDelete()
            openDialog = false
        },
        onDismissRequest = {
            openDialog = false
        },
        dialogTitle = stringResource(R.string.confirm_remove_student_title),
        dialogText = stringResource(R.string.confirm_remove_student_description)
    )

    IconButton(
        onClick = { openDialog = true },
        modifier = modifier.size(24.dp),
    ) {
        Icon(
            imageVector = Icons.Default.Clear,
            contentDescription = stringResource(R.string.delete_subject_fab_description),
            tint = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
private fun ConfirmDeleteDialog(
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
) {
    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text(text = stringResource(R.string.delete))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(text = stringResource(R.string.cancel))
            }
        }
    )

}