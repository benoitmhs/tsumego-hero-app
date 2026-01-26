package com.mrsanglier.tsumegohero.coreui.componants.pulltorefresh

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun THPullToRefreshBox(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
    state: PullToRefreshState = rememberPullToRefreshState(),
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit,
) {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier,
        state = state,
        contentAlignment = contentAlignment,
        indicator = {
            Indicator(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = innerPadding.calculateTopPadding()),
                isRefreshing = isRefreshing,
                state = state,
                containerColor = THTheme.colors.surfaceInvert,
                color = THTheme.colors.contentInvert,
            )
        },
        content = content,
    )
}