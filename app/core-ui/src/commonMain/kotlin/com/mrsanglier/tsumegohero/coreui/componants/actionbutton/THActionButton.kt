package com.mrsanglier.tsumegohero.coreui.componants.actionbutton

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mrsanglier.tsumegohero.coreui.componants.icon.Content
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSize
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec
import com.mrsanglier.tsumegohero.coreui.componants.text.THText
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.extension.thClickable
import com.mrsanglier.tsumegohero.coreui.extension.surface
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
fun THActionButton(
    text: TextSpec,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: ActionButtonStyle = ActionButtonStyle.Primary,
    enabled: Boolean = true,
    icon: IconSpec? = null,
    trailingIcon: IconSpec? = null,
) {
    val focusManager = LocalFocusManager.current

    val contentColor by animateColorAsState(style.contentColor(!enabled))
    val backgroundColor by animateColorAsState(style.backgroundColor(!enabled))
    val strokeColor by animateColorAsState(style.strokeColor(!enabled))

    Box(
        modifier = modifier
            .wrapContentWidth()
            .sizeIn(minHeight = MinHeight)
            .surface(
                shape = THTheme.shape.roundMedium,
                background = backgroundColor,
                borderColor = strokeColor,
                borderWidth = THTheme.stroke.regular,
            )
            .thClickable(
                onClick = {
                    focusManager.clearFocus()
                    onClick()
                },
                enabled = enabled,
                role = Role.Button,
                indication = ripple(color = contentColor),
            ),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .height(MinHeight)
                .padding(
                    horizontal = THTheme.spacing.small,
                    vertical = THTheme.spacing.xsmall,
                ),
            horizontalArrangement = Arrangement.spacedBy(THTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            icon?.Content(
                iconSize = IconSize.Medium,
                tint = contentColor,
            )
            THText(
                text = text,
                style = THTheme.typography.label100Medium,
                color = contentColor,
                maxLines = 1,
            )
            trailingIcon?.Content(
                iconSize = IconSize.Medium,
                tint = contentColor,
            )
        }
    }
}

private val MinHeight: Dp = 36.dp
