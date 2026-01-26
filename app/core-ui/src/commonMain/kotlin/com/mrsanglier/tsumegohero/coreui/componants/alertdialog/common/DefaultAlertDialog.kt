package com.mrsanglier.tsumegohero.coreui.componants.alertdialog.common

import androidx.compose.runtime.Composable
import com.mrsanglier.tsumegohero.coreui.componants.alertdialog.AlertDialogAction
import com.mrsanglier.tsumegohero.coreui.componants.alertdialog.AlertDialogState
import com.mrsanglier.tsumegohero.coreui.componants.text.THText
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

data class DefaultAlertDialog(
    val title: TextSpec?,
    val description: TextSpec,
    override val actions: List<AlertDialogAction>,
    override val onDismiss: (() -> Unit)? = null,
) : AlertDialogState {

    @Composable
    override fun Content(dismiss: () -> Unit) {
        THAlertDialog(
            title = title,
            actions = actions,
            onDismissRequest = {
                onDismiss?.invoke()
                dismiss()
            }
        ) {
            THText(
                text = description,
                style = THTheme.typography.content100,
                color = THTheme.colors.content,
            )
        }
    }
}
