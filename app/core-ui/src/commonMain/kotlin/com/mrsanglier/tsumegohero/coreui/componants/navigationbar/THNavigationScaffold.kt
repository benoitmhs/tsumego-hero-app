package com.mrsanglier.tsumegohero.coreui.componants.navigationbar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.mrsanglier.tsumegohero.coreui.theme.color.Transparent

@Composable
fun THNavigationBarScaffold(
    navigationItems: List<FONavigationBarItemData>,
    topBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {

    Scaffold(
        topBar = topBar,
        backgroundColor = Transparent,
        bottomBar = {
            THNavigationBar(
                items = navigationItems,
            )
        },
    ) {
        content(it)
    }
}