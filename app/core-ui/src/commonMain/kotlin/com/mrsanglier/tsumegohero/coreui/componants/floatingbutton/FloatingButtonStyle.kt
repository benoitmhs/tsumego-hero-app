package com.mrsanglier.tsumegohero.coreui.componants.floatingbutton

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.mrsanglier.tsumegohero.coreui.extension.composed
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

sealed class FloatingButtonStyle(
    val contentColor: @Composable () -> Color,
    val backgroundColor: @Composable () -> Color,
) {
    data object Primary : FloatingButtonStyle(
        contentColor = THTheme.composed { colors.contentOnSurfaceTint },
        backgroundColor = THTheme.composed { colors.surfaceAccent },
    )

    data object Secondary : FloatingButtonStyle(
        contentColor = THTheme.composed { colors.content },
        backgroundColor = THTheme.composed { colors.surface1 },
    )
}
