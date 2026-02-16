package com.mrsanglier.tsumegohero.coreui.componants.bottombar

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.mrsanglier.tsumegohero.coreui.componants.button.ButtonStyle
import com.mrsanglier.tsumegohero.coreui.componants.button.THButtonState
import com.mrsanglier.tsumegohero.coreui.componants.icon.Content
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSize
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec
import com.mrsanglier.tsumegohero.coreui.componants.text.THText
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.extension.foHazeChild
import com.mrsanglier.tsumegohero.coreui.theme.THTheme
import dev.chrisbanes.haze.HazeState

@Composable
fun THBottomBar(
    hazeState: HazeState,
    elevation: Dp,
    modifier: Modifier = Modifier,
    title: TextSpec? = null,
    icon: IconSpec? = null,
    description: TextSpec? = null,
    primaryButton: THButtonState? = null,
    secondaryButton: THButtonState? = null,
) {
    val backgroundColor by animateColorAsState(if (elevation == 0.dp) THTheme.colors.background else THTheme.colors.surfaceBlur)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .foHazeChild(
                state = hazeState,
                backgroundColor = backgroundColor,
            )
            .navigationBarsPadding()
            .padding(THTheme.spacing.large)
            .zIndex(1f),
        verticalArrangement = Arrangement.spacedBy(THTheme.spacing.large),
    ) {
        // Header
        if (title != null || description != null) {
            Column(
                verticalArrangement = Arrangement.spacedBy(THTheme.spacing.tiny),
            ) {
                title?.let {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(THTheme.spacing.tiny),
                    ) {
                        icon?.Content(
                            iconSize = IconSize.Regular,
                        )
                        THText(
                            text = title,
                            style = THTheme.typography.title100,
                        )
                    }
                }
                description?.let {
                    THText(
                        text = description,
                        style = THTheme.typography.content100,
                    )
                }
            }
        }

        // Actions
        if (primaryButton != null || secondaryButton != null) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(THTheme.spacing.large),
            ) {
                primaryButton?.copy(
                    style = ButtonStyle.Primary,
                )?.Content(
                    modifier = Modifier.weight(1f),
                )
                secondaryButton?.copy(
                    style = ButtonStyle.Secondary,
                )?.Content(
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}
