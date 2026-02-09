package com.mrsanglier.tsumegohero.coreui.componants.floatingbutton

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.mrsanglier.tsumegohero.coreui.componants.icon.Content
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSize
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec
import com.mrsanglier.tsumegohero.coreui.extension.thClickable
import com.mrsanglier.tsumegohero.coreui.extension.surface
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
fun RoundFloatingButton(
    iconSpec: IconSpec,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: FloatingButtonStyle = FloatingButtonStyle.Primary,
) {
    val focusManager = LocalFocusManager.current
    val contentColor = style.contentColor()

    Box(
        modifier = modifier
            .size(FloatingButtonSize)
            .surface(
                shape = THTheme.shape.circle,
                background = style.backgroundColor(),
                elevation = THTheme.elevation.medium,
            )
            .thClickable(
                onClick = {
                    focusManager.clearFocus()
                    onClick()
                },
                role = Role.Button,
                indication = ripple(color = contentColor),
            )
            .zIndex(5f),
        contentAlignment = Alignment.Center,
    ) {
        iconSpec.Content(
            iconSize = IconSize.Medium,
            tint = contentColor,
        )
    }
}

val FloatingButtonSize: Dp = 56.dp