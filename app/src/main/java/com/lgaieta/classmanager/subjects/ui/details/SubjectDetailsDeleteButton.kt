package com.lgaieta.classmanager.subjects.ui.details

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lgaieta.classmanager.R

@Composable
fun SubjectDetailsDeleteButton(onDelete: () -> Unit) {
    var openDialog by rememberSaveable { mutableStateOf(false) }
    if (openDialog) ConfirmDeleteDialog(
        onConfirmation = {
            onDelete()
            openDialog = false
        },
        onDismissRequest = {
            openDialog = false
        },
        dialogTitle = stringResource(R.string.confirm_delete_subject_title),
        dialogText = stringResource(R.string.confirm_delete_subject_description)
    )
    FloatingActionButton(
        onClick = { openDialog = true },
        elevation = FloatingActionButtonDefaults.elevation(0.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = stringResource(R.string.delete_subject_fab_description),
            tint = MaterialTheme.colorScheme.primary
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
                Text(text = stringResource(R.string.confirm_delete_subject_button))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(text = stringResource(R.string.cancel_delete_subject))
            }
        }
    )

}