package com.mrsanglier.tsumegohero.coreui.componants.snackbar.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSize
import com.mrsanglier.tsumegohero.coreui.componants.icon.THIcon
import com.mrsanglier.tsumegohero.coreui.componants.snackbar.THSnackbarVisual
import com.mrsanglier.tsumegohero.coreui.componants.snackbar.toIconSpec
import com.mrsanglier.tsumegohero.coreui.componants.text.THText
import com.mrsanglier.tsumegohero.coreui.extension.foClickable
import com.mrsanglier.tsumegohero.coreui.extension.surface
import com.mrsanglier.tsumegohero.coreui.extension.toTextSpec
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
internal fun ShortSnackbar(
    snackbarVisual: THSnackbarVisual.Short,
    dismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .heightIn(min = minHeight)
            .surface(
                shape = THTheme.shape.circle,
                background = THTheme.colors.surface3,
            )
            .foClickable(dismiss)
            .padding(vertical = THTheme.spacing.small)
            .padding(
                start = if (snackbarVisual.icon == null) THTheme.spacing.large else THTheme.spacing.small,
                end = THTheme.spacing.large,
            ),
        horizontalArrangement = Arrangement.spacedBy(THTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        snackbarVisual.icon?.toIconSpec()?.let { icon ->
            THIcon(
                icon = icon,
                iconSize = IconSize.Small,
                tint = THTheme.colors.content,
            )
        }

        THText(
            text = snackbarVisual.text.toTextSpec(),
            style = THTheme.typography.label100,
            color = THTheme.colors.content,
        )
    }
}

private val minHeight: Dp = 24.dp