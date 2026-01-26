package com.mrsanglier.tsumegohero.coreui.componants.alertdialog.common

import com.mrsanglier.tsumegohero.app.coreui.resources.common_cancel
import com.mrsanglier.tsumegohero.app.coreui.resources.common_validate
import com.mrsanglier.tsumegohero.coreui.componants.alertdialog.AlertDialogAction
import com.mrsanglier.tsumegohero.coreui.extension.toTextSpec
import com.mrsanglier.tsumegohero.coreui.resources.THString

object CommonAlertDialogAction {
    fun cancel(onClick: () -> Unit = {}): AlertDialogAction {
        return AlertDialogAction(
            text = THString.common_cancel.toTextSpec(),
            type = AlertDialogAction.Type.Cancel,
            onClick = onClick,
        )
    }

    fun validate(
        enabled: Boolean = true,
        onClick: () -> Unit,
    ): AlertDialogAction =
        AlertDialogAction(
            text = THString.common_validate.toTextSpec(),
            type = AlertDialogAction.Type.Confirm,
            enabled = enabled,
            onClick = onClick,
        )
}
