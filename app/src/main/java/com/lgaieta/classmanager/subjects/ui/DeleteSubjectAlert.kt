package com.lgaieta.classmanager.subjects.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.lgaieta.classmanager.R

@Preview(showSystemUi = true)
@Composable
fun AlertDialogExample(
) {
    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            TextButton(
                onClick = {
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
        },
        title = {
            Text(text =  stringResource(R.string.delete_subject),
            )
        }


        )
}

//@Composable
//fun DialogExamples() {
//  val openAlertDialog = remember { mutableStateOf(false) }

//  when {
// ...
//      openAlertDialog.value -> {
//          AlertDialogExample(
//              onDismissRequest = { openAlertDialog.value = false },
//              onConfirmation = {
//                  openAlertDialog.value = false
//                  println("Confirmation registered") // Add logic here to handle confirmation.
//             },
//         dialogTitle = "Alert dialog example",
//              dialogText = "This is an example of an alert dialog with buttons.",
//          )
//      }
//  }
//}