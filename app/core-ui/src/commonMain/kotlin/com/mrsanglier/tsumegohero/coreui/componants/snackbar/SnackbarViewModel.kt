package com.mrsanglier.tsumegohero.coreui.componants.snackbar

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class SnackbarViewModel(
    private val snackbarManager: SnackbarManager,
) : ViewModel() {
    val shownSnackBar: StateFlow<THSnackbarState?> = snackbarManager.shownSnackbar
    fun consumeSnackBar(): Unit = snackbarManager.consumeSnackBar()
}
