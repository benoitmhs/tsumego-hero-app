package com.mrsanglier.tsumegohero.coreui.componants.iconbutton

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec
import com.mrsanglier.tsumegohero.coreui.componants.icon.THIcon
import com.mrsanglier.tsumegohero.coreui.extension.thClickable
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
fun FOIconButton(
    icon: IconSpec,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: IconButtonStyle = IconButtonStyle.Classical,
    size: IconButtonSize = IconButtonSize.Regular,
) {
    val contentColor by animateColorAsState(style.contentColor(enabled))

    Box(
        modifier = modifier
            .size(size.containerSize)
            .clip(THTheme.shape.circle)
            .thClickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                indication = ripple(color = contentColor),
            ),
        contentAlignment = Alignment.Center
    ) {
        THIcon(
            icon = icon,
            iconSize = size.iconSize,
            tint = contentColor,
        )
    }
}
