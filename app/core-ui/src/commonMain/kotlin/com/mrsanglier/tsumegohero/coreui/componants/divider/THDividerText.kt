package com.mrsanglier.tsumegohero.coreui.componants.divider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mrsanglier.tsumegohero.coreui.componants.text.THText
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
fun THDividerText(
    text: TextSpec,
    modifier: Modifier = Modifier,
    color: Color = THTheme.colors.strokeDivider,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(THTheme.spacing.tiny),
    ) {
        THDividerHorizontal(
            modifier = Modifier.weight(1f),
            color = color,
        )
        THText(
            text = text,
            style = THTheme.typography.content50,
            color = color,
        )
        THDividerHorizontal(
            modifier = Modifier.weight(1f),
            color = color,
        )
    }
}
