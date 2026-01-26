package com.mrsanglier.tsumegohero.coreui.componants.navigationbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
fun THNavigationBar(
    items: List<FONavigationBarItemData>,
    modifier: Modifier = Modifier,
) {
    Column {
        BottomAppBar(
            backgroundColor = THTheme.colors.surface1,
            contentColor = THTheme.colors.content,
            modifier = modifier.zIndex(1f),
        ) {
            items.forEach {
                THNavigationBarItem(it)
            }
        }
        Box(
            modifier = Modifier
                .background(THTheme.colors.surface1)
                .navigationBarsPadding()
                .fillMaxWidth()
                .zIndex(2f),
        )
    }
}
