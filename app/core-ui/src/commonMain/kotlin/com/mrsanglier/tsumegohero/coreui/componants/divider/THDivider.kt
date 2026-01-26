package com.mrsanglier.tsumegohero.coreui.componants.divider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
fun THDividerHorizontal(
    modifier: Modifier = Modifier,
    color: Color = THTheme.colors.strokeDivider,
) {
    Divider(
        modifier = modifier.fillMaxWidth(),
        color = color,
        thickness = THTheme.stroke.xthin,
    )
}

@Composable
fun THDividerVertical(
    modifier: Modifier = Modifier,
    color: Color = THTheme.colors.strokeDivider,
) {
    Box(
        modifier
            .fillMaxHeight()
            .width(THTheme.stroke.xthin)
            .background(color = color)
    )
}
