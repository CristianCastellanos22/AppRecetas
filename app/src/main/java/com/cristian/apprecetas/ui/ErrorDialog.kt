package com.cristian.apprecetas.ui

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.cristian.apprecetas.R

@Composable
fun ErrorDialog(
    messageId: String,
    onDialogDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(text = stringResource(id = R.string.error_dialog_title))
        },
        text = {
            Text(messageId)
        }, confirmButton = {
            Button(
                onClick = { onDialogDismiss() },
            ) {
                Text(text = stringResource(R.string.try_again))
            }
        }
    )
}