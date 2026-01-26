package com.mrsanglier.tsumegohero.coreui.componants.alertdialog

import androidx.compose.runtime.Composable

interface AlertDialogState {

    val actions: List<AlertDialogAction>
    val onDismiss: (() -> Unit)?

    @Composable
    fun Content(dismiss: () -> Unit)
}