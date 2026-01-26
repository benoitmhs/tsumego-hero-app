package com.mrsanglier.tsumegohero.coreui.componants.snackbar

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SnackbarManager {
    private val _shownSnackbar: MutableStateFlow<THSnackbarState?> = MutableStateFlow(null)
    val shownSnackbar: StateFlow<THSnackbarState?> = _shownSnackbar.asStateFlow()

    fun showSnackBar(data: THSnackbarState) {
        _shownSnackbar.value = data
    }

    fun consumeSnackBar() {
        _shownSnackbar.value = null
    }
}
