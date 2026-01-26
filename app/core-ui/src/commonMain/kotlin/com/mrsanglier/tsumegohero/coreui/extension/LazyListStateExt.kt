package com.mrsanglier.tsumegohero.coreui.extension

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.material.AppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun rememberTopBarElevation(lazyListState: LazyListState): State<Dp> =
    remember { derivedStateOf { lazyListState.topAppBarElevation } }

@Composable
fun rememberTopBarElevation(lazyGridState: LazyGridState): State<Dp> =
    remember { derivedStateOf { lazyGridState.topAppBarElevation } }

@Composable
fun rememberBottomBarElevation(lazyListState: LazyListState): State<Dp> =
    remember { derivedStateOf { lazyListState.bottomBarElevation } }

private val LazyGridState.topAppBarElevation: Dp
    get() = if (firstVisibleItemIndex == 0) {
        minOf(firstVisibleItemScrollOffset.toFloat().dp, AppBarDefaults.TopAppBarElevation)
    } else {
        AppBarDefaults.TopAppBarElevation
    }

private val LazyListState.topAppBarElevation: Dp
    get() = if (firstVisibleItemIndex == 0) {
        minOf(firstVisibleItemScrollOffset.toFloat().dp, AppBarDefaults.TopAppBarElevation)
    } else {
        AppBarDefaults.TopAppBarElevation
    }

private val LazyListState.bottomBarElevation: Dp
    get() = if (canScrollForward) {
        AppBarDefaults.BottomAppBarElevation
    } else {
        0.dp
    }
