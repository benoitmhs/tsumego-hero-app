package com.mrsanglier.tsumegohero.coreui.theme

import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.mrsanglier.tsumegohero.coreui.theme.color.DefaultDarkColorScheme
import com.mrsanglier.tsumegohero.coreui.theme.color.THColorScheme
import com.mrsanglier.tsumegohero.coreui.theme.color.THGradient
import com.mrsanglier.tsumegohero.coreui.theme.dimens.THElevation
import com.mrsanglier.tsumegohero.coreui.theme.dimens.THSpacing
import com.mrsanglier.tsumegohero.coreui.theme.dimens.THStroke
import com.mrsanglier.tsumegohero.coreui.theme.shape.THShape
import com.mrsanglier.tsumegohero.coreui.theme.typography.THTypography

val LocalTHColors: ProvidableCompositionLocal<THColorScheme> = staticCompositionLocalOf { error("no provided") }
val LocalTHTypography: ProvidableCompositionLocal<THTypography> = staticCompositionLocalOf { error("no provided") }
val LocalTHSpacing: ProvidableCompositionLocal<THSpacing> = staticCompositionLocalOf { error("no provided") }
val LocalTHStroke: ProvidableCompositionLocal<THStroke> = staticCompositionLocalOf { error("no provided") }
val LocalTHShape: ProvidableCompositionLocal<THShape> = staticCompositionLocalOf { error("no provided") }
val LocalTHElevation: ProvidableCompositionLocal<THElevation> = staticCompositionLocalOf { error("no provided") }
val LocalTHGradient: ProvidableCompositionLocal<THGradient> = staticCompositionLocalOf { error("no provided") }

@Composable
fun THTheme(
    content: @Composable () -> Unit,
) {
    val colorScheme = DefaultDarkColorScheme
    val textSelectionColors = TextSelectionColors(
        handleColor = colorScheme.contentTint,
        backgroundColor = colorScheme.contentTint.copy(alpha = 0.4f),
    )

    MaterialTheme {
        CompositionLocalProvider(
            LocalTHColors provides colorScheme,
            LocalTHTypography provides THTypography,
            LocalTHSpacing provides THSpacing,
            LocalTHStroke provides THStroke,
            LocalTHShape provides THShape,
            LocalTHElevation provides THElevation,
            LocalTextSelectionColors provides textSelectionColors,
            LocalTHGradient provides THGradient,
        ) {
            content()
        }
    }
}

object THTheme {
    val colors: THColorScheme
        @Composable
        get() = LocalTHColors.current

    val typography: THTypography
        @Composable
        get() = LocalTHTypography.current

    val spacing: THSpacing
        @Composable
        get() = LocalTHSpacing.current

    val stroke: THStroke
        @Composable
        get() = LocalTHStroke.current

    val shape: THShape
        @Composable
        get() = LocalTHShape.current

    val elevation: THElevation
        @Composable
        get() = LocalTHElevation.current

    val gradient: THGradient
        @Composable
        get() = LocalTHGradient.current
}
