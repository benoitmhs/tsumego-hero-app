package com.mrsanglier.tsumegohero.coreui.componants.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.mrsanglier.tsumegohero.coreui.componants.background.BackgroundSpec
import com.mrsanglier.tsumegohero.coreui.extension.asBackground
import com.mrsanglier.tsumegohero.coreui.extension.composed
import com.mrsanglier.tsumegohero.coreui.theme.THTheme
import com.mrsanglier.tsumegohero.coreui.theme.color.Transparent

sealed class ButtonStyle(
    protected val contentColor: @Composable () -> Color,
    protected val background: @Composable () -> BackgroundSpec?,
    protected val strokeColor: @Composable () -> Color,
    protected val disableContentColor: @Composable () -> Color,
    protected val disableBackgroundColor: @Composable () -> BackgroundSpec?,
    protected val disableStrokeColor: @Composable () -> Color,
    internal val loadingColor: @Composable () -> Color,
) {
    @Composable
    fun contentColor(buttonStatus: ButtonStatus): Color = when (buttonStatus) {
        ButtonStatus.Disabled -> disableContentColor()
        else -> contentColor()
    }

    @Composable
    fun background(buttonStatus: ButtonStatus): BackgroundSpec? = when (buttonStatus) {
        ButtonStatus.Enabled -> background()
        ButtonStatus.Disabled -> disableBackgroundColor()
        else -> null
    }

    @Composable
    fun strokeColor(buttonStatus: ButtonStatus): Color = when (buttonStatus) {
        ButtonStatus.Enabled -> strokeColor()
        ButtonStatus.Disabled -> disableStrokeColor()
        else -> Transparent
    }

    data object Primary : ButtonStyle(
        contentColor = THTheme.composed { colors.contentOnSurfaceTint },
        background = THTheme.composed { gradient.accent.asBackground() },
        strokeColor = { Transparent },
        disableContentColor = THTheme.composed { colors.contentDisable },
        disableBackgroundColor = THTheme.composed { colors.surfaceDisable.asBackground() },
        disableStrokeColor = { Transparent },
        loadingColor = THTheme.composed { colors.contentTint },
    )

    data object Secondary : ButtonStyle(
        contentColor = THTheme.composed { colors.content },
        background = THTheme.composed { colors.surface3.asBackground() },
        strokeColor = { Transparent },
        disableContentColor = THTheme.composed { colors.contentDisable },
        disableBackgroundColor = THTheme.composed { colors.surfaceDisable.asBackground() },
        disableStrokeColor = { Transparent },
        loadingColor = THTheme.composed { colors.content },
    )

    data object Outlined : ButtonStyle(
        contentColor = THTheme.composed { colors.content },
        background = { null },
        strokeColor = THTheme.composed { colors.stroke },
        disableContentColor = THTheme.composed { colors.contentDisable },
        disableBackgroundColor = { null },
        disableStrokeColor = THTheme.composed { colors.strokeDisable },
        loadingColor = { THTheme.colors.content },
    )

    data object Text : ButtonStyle(
        contentColor = THTheme.composed { colors.content },
        background = { null },
        strokeColor = { Transparent },
        disableContentColor = THTheme.composed { colors.contentDisable },
        disableBackgroundColor = { null },
        disableStrokeColor = { Transparent },
        loadingColor = THTheme.composed { colors.content },
    )

    data object Critical : ButtonStyle(
        contentColor = THTheme.composed { colors.contentOnSurfaceTint },
        background = THTheme.composed { colors.surfaceCritical.asBackground() },
        strokeColor = { Transparent },
        disableContentColor = THTheme.composed { colors.contentDisable },
        disableBackgroundColor = THTheme.composed { colors.surfaceDisable.asBackground() },
        disableStrokeColor = { Transparent },
        loadingColor = { THTheme.colors.contentCritical },
    )
}
