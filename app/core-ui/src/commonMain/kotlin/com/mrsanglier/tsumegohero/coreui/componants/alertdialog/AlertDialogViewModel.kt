package com.mrsanglier.tsumegohero.coreui.componants.alertdialog

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class AlertDialogViewModel(
    private val alertDialogManager: AlertDialogManager,
) : ViewModel() {

    val dialogState: StateFlow<AlertDialogState?> = alertDialogManager.dialogState

    fun closeDialog(): Unit = alertDialogManager.closeDialog()
}
