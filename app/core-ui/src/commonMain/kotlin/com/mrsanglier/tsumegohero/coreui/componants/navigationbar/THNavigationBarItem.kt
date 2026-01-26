package com.mrsanglier.tsumegohero.coreui.componants.navigationbar

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.mrsanglier.tsumegohero.coreui.componants.icon.Content
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSize
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec
import com.mrsanglier.tsumegohero.coreui.componants.text.THTextResponsive
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
internal fun RowScope.THNavigationBarItem(
    itemData: FONavigationBarItemData,
    withoutIcon: Boolean = false,
    modifier: Modifier = Modifier,
) {
    val contentColor by animateColorAsState(
        if (itemData.isSelected) THTheme.colors.contentTint else THTheme.colors.content,
    )
    val textStyle = if (itemData.isSelected) THTheme.typography.label50Semibold else THTheme.typography.label50Light

    BottomNavigationItem(
        modifier = modifier,
        selected = itemData.isSelected,
        onClick = itemData.onClick,
        selectedContentColor = THTheme.colors.contentTint,
        unselectedContentColor = THTheme.colors.content,
        icon = {
            if (withoutIcon) {
                Box(modifier.size(IconSize.Navigation.dp)) {}
            } else {
                Crossfade(
                    if (itemData.isSelected) {
                        itemData.selectedIcon to THTheme.colors.contentTint
                    } else {
                        itemData.unselectedIcon to THTheme.colors.content
                    }
                ) { (icon, tint) ->
                    icon.Content(
                        iconSize = IconSize.Navigation,
                        tint = tint,
                    )
                }
            }
        },
        label = {
            THTextResponsive(
                text = itemData.label,
                textAlign = TextAlign.Center,
                style = textStyle,
                color = contentColor,
                maxLines = 1,
            )
        },
    )
}

data class FONavigationBarItemData(
    val label: TextSpec,
    val selectedIcon: IconSpec,
    val unselectedIcon: IconSpec,
    val isSelected: Boolean,
    val onClick: () -> Unit,
)
