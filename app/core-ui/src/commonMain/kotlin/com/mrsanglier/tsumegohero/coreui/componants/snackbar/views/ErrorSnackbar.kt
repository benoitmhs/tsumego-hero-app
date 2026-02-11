package com.mrsanglier.tsumegohero.coreui.componants.snackbar.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mrsanglier.tsumegohero.app.coreui.resources.ic_close
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSize
import com.mrsanglier.tsumegohero.coreui.componants.icon.THIcon
import com.mrsanglier.tsumegohero.coreui.componants.snackbar.THSnackbarVisual
import com.mrsanglier.tsumegohero.coreui.componants.text.THText
import com.mrsanglier.tsumegohero.coreui.extension.thClickable
import com.mrsanglier.tsumegohero.coreui.extension.surface
import com.mrsanglier.tsumegohero.coreui.extension.toIconSpec
import com.mrsanglier.tsumegohero.coreui.extension.toTextSpec
import com.mrsanglier.tsumegohero.coreui.resources.THDrawable
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
internal fun ErrorSnackbar(
    snackbarVisual: THSnackbarVisual.Error,
    dismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = minHeight)
            .surface(
                shape = THTheme.shape.roundMedium,
                background = THTheme.colors.surfaceCritical,
            )
            .thClickable(dismiss)
            .padding(
                vertical = THTheme.spacing.small,
                horizontal = THTheme.spacing.medium,
            ),
        horizontalArrangement = Arrangement.spacedBy(THTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        THText(
            text = snackbarVisual.text.toTextSpec(),
            style = THTheme.typography.label100,
            color = THTheme.colors.contentOnSurfaceTint,
            modifier = Modifier.weight(1f),
        )

        THIcon(
            icon = THDrawable.ic_close.toIconSpec(),
            iconSize = IconSize.Small,
            tint = THTheme.colors.content,
        )
    }
}

private val minHeight: Dp = 40.dp