package com.lgaieta.classmanager.subjects.ui.delete

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lgaieta.classmanager.R

@Preview(showSystemUi = true)
@Composable
fun DeleteSubjectAlert(
    modifier: Modifier = Modifier,
    DeleteSubjectViewModel: DeleteSubjectViewModel = viewModel(),
    onDelete: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(
                text = stringResource(R.string.delete_subject),
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDelete()
                }
            ) {
                Text(
                    text = stringResource(R.string.delete),
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {}
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                )
            }
        }
    )
}

