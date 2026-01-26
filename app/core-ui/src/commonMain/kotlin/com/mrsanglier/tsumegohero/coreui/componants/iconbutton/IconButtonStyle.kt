package com.mrsanglier.tsumegohero.coreui.componants.iconbutton

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.mrsanglier.tsumegohero.coreui.extension.composed
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

sealed class IconButtonStyle(
    protected val contentColor: @Composable () -> Color,
    protected val disableContentColor: @Composable () -> Color,
) {
    @Composable
    fun contentColor(isEnabled: Boolean): Color = if (isEnabled) {
        contentColor()
    } else {
        disableContentColor()
    }

    data object Classical : IconButtonStyle(
        contentColor = THTheme.composed { colors.content },
        disableContentColor = THTheme.composed { colors.contentDisable },
    )

    data object Critical : IconButtonStyle(
        contentColor = THTheme.composed { colors.contentCritical },
        disableContentColor = THTheme.composed { colors.contentDisable },
    )
}
