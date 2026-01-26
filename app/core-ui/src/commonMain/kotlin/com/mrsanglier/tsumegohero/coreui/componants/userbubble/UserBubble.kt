package com.mrsanglier.tsumegohero.coreui.componants.userbubble

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.mrsanglier.tsumegohero.coreui.componants.text.THTextResponsive
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.extension.surface
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
fun UserBubble(
    label: TextSpec,
    backgroundColor: Color,
    borderColor: Color?,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = THTheme.typography.label200,
    textColor: Color = THTheme.colors.content,
) {
    Box(
        modifier = modifier
            .size(36.dp)
            .surface(
                background = backgroundColor,
                shape = THTheme.shape.circle,
                borderWidth = THTheme.stroke.thin,
                borderColor = borderColor,
            ),
        contentAlignment = Alignment.Center,
    ) {
        THTextResponsive(
            text = label,
            style = textStyle,
            modifier = Modifier.padding(
                vertical = THTheme.spacing.tiny,
                horizontal = THTheme.spacing.small,
            ),
            color = textColor,
        )
    }
}

@Immutable
data class UserBubbleState(
    val label: TextSpec,
    val backgroundColor: Color,
)

@Composable
fun UserBubbleState.Content(
    modifier: Modifier = Modifier,
    borderColor: Color? = null,
) {
    UserBubble(
        label = label,
        backgroundColor = backgroundColor,
        borderColor = borderColor,
        modifier = modifier,
    )
}
