package com.mrsanglier.tsumegohero.coreui.componants.actionbutton

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.mrsanglier.tsumegohero.coreui.extension.composed
import com.mrsanglier.tsumegohero.coreui.theme.THTheme
import com.mrsanglier.tsumegohero.coreui.theme.color.Transparent

sealed class ActionButtonStyle(
    protected val contentColor: @Composable () -> Color,
    protected val backgroundColor: @Composable () -> Color,
    protected val strokeColor: @Composable () -> Color,
    protected val disableContentColor: @Composable () -> Color,
    protected val disableBackgroundColor: @Composable () -> Color,
    protected val disableStrokeColor: @Composable () -> Color,
) {
    @Composable
    fun contentColor(isDisabled: Boolean): Color =
        if (isDisabled) disableContentColor() else contentColor()

    @Composable
    fun backgroundColor(isDisabled: Boolean): Color =
        if (isDisabled) disableBackgroundColor() else backgroundColor()

    @Composable
    fun strokeColor(isDisabled: Boolean): Color =
        if (isDisabled) disableStrokeColor() else strokeColor()

    data object Primary : ActionButtonStyle(
        contentColor = THTheme.composed { colors.contentOnSurfaceTint },
        backgroundColor = THTheme.composed { colors.surfaceAccent },
        strokeColor = { Transparent },
        disableContentColor = THTheme.composed { colors.contentDisable },
        disableBackgroundColor = THTheme.composed { colors.surfaceDisable },
        disableStrokeColor = { Transparent },
    )

    data object Outlined : ActionButtonStyle(
        contentColor = THTheme.composed { colors.content },
        backgroundColor = THTheme.composed { Transparent },
        strokeColor = THTheme.composed { colors.stroke },
        disableContentColor = THTheme.composed { colors.contentDisable },
        disableBackgroundColor = { Transparent },
        disableStrokeColor = THTheme.composed { colors.strokeDisable },
    )

    data object Text : ActionButtonStyle(
        contentColor = THTheme.composed { colors.content },
        backgroundColor = THTheme.composed { Transparent },
        strokeColor = { Transparent },
        disableContentColor = THTheme.composed { colors.contentDisable },
        disableBackgroundColor = { Transparent },
        disableStrokeColor = { Transparent },
    )

    data object TextTint : ActionButtonStyle(
        contentColor = THTheme.composed { colors.contentTint },
        backgroundColor = THTheme.composed { Transparent },
        strokeColor = { Transparent },
        disableContentColor = THTheme.composed { colors.contentDisable },
        disableBackgroundColor = { Transparent },
        disableStrokeColor = { Transparent },
    )

    data object TextCritical : ActionButtonStyle(
        contentColor = THTheme.composed { colors.contentCritical },
        backgroundColor = THTheme.composed { Transparent },
        strokeColor = { Transparent },
        disableContentColor = THTheme.composed { colors.contentDisable },
        disableBackgroundColor = { Transparent },
        disableStrokeColor = { Transparent },
    )
}
