package com.mrsanglier.tsumegohero.coreui.theme.color

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

object THGradient {
    val accent: Brush
        @Composable get() = Brush.linearGradient(
            colors = listOf(
                THTheme.colors.surfaceAccentGradient,
                THTheme.colors.surfaceAccent,
            ),
        )
}
