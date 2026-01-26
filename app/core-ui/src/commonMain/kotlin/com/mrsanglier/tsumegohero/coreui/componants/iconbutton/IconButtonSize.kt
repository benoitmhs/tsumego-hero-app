package com.mrsanglier.tsumegohero.coreui.componants.iconbutton

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSize

enum class IconButtonSize(
    val iconSize: IconSize,
    val containerSize: Dp,
) {
    Small(iconSize = IconSize.Small, containerSize = 32.dp),
    Regular(iconSize = IconSize.Regular, containerSize = 40.dp),
}