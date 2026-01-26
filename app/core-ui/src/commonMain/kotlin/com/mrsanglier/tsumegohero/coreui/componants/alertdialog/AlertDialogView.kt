package com.mrsanglier.tsumegohero.coreui.componants.alertdialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun AlertDialogView(
    viewModel: AlertDialogViewModel,
) {
    val alertDialogState by viewModel.dialogState.collectAsStateWithLifecycle()

    alertDialogState?.Content(
        dismiss = viewModel::closeDialog,
    )
}
