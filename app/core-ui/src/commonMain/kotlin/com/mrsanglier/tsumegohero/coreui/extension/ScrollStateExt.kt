package com.mrsanglier.tsumegohero.coreui.extension

import androidx.compose.foundation.ScrollState
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.AppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun rememberTopBarElevation(scrollState: ScrollState): State<Dp> =
    remember { derivedStateOf { scrollState.topAppBarElevation } }

@Composable
fun rememberBottomBarElevation(scrollState: ScrollState): State<Dp> =
    remember { derivedStateOf { scrollState.bottomBarElevation } }

private val ScrollState.topAppBarElevation: Dp
    get() = if (value == 0) {
        0.dp
    } else {
        AppBarDefaults.TopAppBarElevation
    }

private val ScrollState.bottomBarElevation: Dp
    get() = if (value == this.maxValue) {
        0.dp
    } else {
        AppBarDefaults.BottomAppBarElevation
    }
