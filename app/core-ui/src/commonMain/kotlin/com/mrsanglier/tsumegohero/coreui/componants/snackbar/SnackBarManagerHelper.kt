package com.mrsanglier.tsumegohero.coreui.componants.snackbar

import com.mrsanglier.tsumegohero.core.error.THError
import com.mrsanglier.tsumegohero.core.result.THResult
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.extension.messageText

fun SnackbarManager.showDone(text: TextSpec) {
    showSnackBar(
        THSnackbarState.Short(
            text = text,
            icon = SnackBarIcon.Done,
        )
    )
}

fun SnackbarManager.showErrorOnFailure(result: THResult<*>) {
    (result as? THResult.Failure)?.error?.let(::showError)
}

fun SnackbarManager.showError(error: THError?) {
    error?.messageText()?.let { message ->
        showSnackBar(
            THSnackbarState.Error(
                text = message,
            )
        )
    }
}
