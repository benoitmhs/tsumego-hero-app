package com.mrsanglier.tsumegohero.coreui.componants.alertdialog.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.mrsanglier.tsumegohero.coreui.componants.alertdialog.AlertDialogAction
import com.mrsanglier.tsumegohero.coreui.componants.spacer.THVerticalSpacer
import com.mrsanglier.tsumegohero.coreui.componants.text.THText
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.extension.surface
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
fun THAlertDialog(
    title: TextSpec?,
    onDismissRequest: () -> Unit,
    actions: List<AlertDialogAction>,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = modifier
                .surface(
                    shape = THTheme.shape.roundMedium,
                    background = THTheme.colors.surface1,
                )
                .padding(horizontal = THTheme.spacing.large)
                .padding(
                    top = THTheme.spacing.large,
                    bottom = THTheme.spacing.medium,
                )
        ) {
            title?.let {
                THText(
                    text = title,
                    style = THTheme.typography.title100,
                    color = THTheme.colors.content,
                )
            }
            THTheme.spacing.medium.THVerticalSpacer()

            content()

            THTheme.spacing.xlarge.THVerticalSpacer()

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    space = THTheme.spacing.small,
                    alignment = Alignment.End,
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                actions.forEach { it.Content(onDismissRequest) }
            }
        }
    }
}
