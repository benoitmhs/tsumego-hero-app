package com.mrsanglier.tsumegohero.coreui.componants.icon

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
fun THIcon(
    icon: IconSpec,
    iconSize: IconSize,
    modifier: Modifier = Modifier,
    tint: Color = THTheme.colors.content,
) {
    Icon(
        painter = icon.painter(),
        contentDescription = icon.contentDescription?.string,
        modifier = modifier.size(iconSize.dp),
        tint = icon.tint?.invoke() ?: tint,
    )
}

@Composable
fun IconSpec.Content(
    iconSize: IconSize,
    tint: Color = THTheme.colors.content,
    modifier: Modifier = Modifier,
) {
    THIcon(
        icon = this,
        iconSize = iconSize,
        modifier = modifier,
        tint = this.tint?.invoke() ?: tint,
    )
}
