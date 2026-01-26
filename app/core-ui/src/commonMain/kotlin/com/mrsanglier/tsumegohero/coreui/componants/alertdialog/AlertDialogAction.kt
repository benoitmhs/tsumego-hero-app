package com.mrsanglier.tsumegohero.coreui.componants.alertdialog

import androidx.compose.runtime.Composable
import com.mrsanglier.tsumegohero.coreui.componants.actionbutton.ActionButtonStyle
import com.mrsanglier.tsumegohero.coreui.componants.actionbutton.THActionButton
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec

data class AlertDialogAction(
    val text: TextSpec,
    val type: Type = Type.Neutral,
    val onClick: () -> Unit,
    val enabled: Boolean = true,
) {
    enum class Type {
        Cancel, Neutral, Confirm, Dangerous;
    }

    @Composable
    fun Content(dismissDialog: () -> Unit) {
        THActionButton(
            text = text,
            onClick = {
                onClick()
                dismissDialog()
            },
            enabled = enabled,
            style = when (type) {
                Type.Cancel,
                Type.Neutral,
                    -> ActionButtonStyle.Text

                Type.Confirm -> ActionButtonStyle.TextTint
                Type.Dangerous -> ActionButtonStyle.TextCritical
            },
        )
    }
}