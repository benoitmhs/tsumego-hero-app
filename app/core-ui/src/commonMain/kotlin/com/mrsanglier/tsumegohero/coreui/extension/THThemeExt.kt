package com.mrsanglier.tsumegohero.coreui.extension

import androidx.compose.runtime.Composable
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

fun <T> THTheme.composed(
    factory: @Composable THTheme.() -> T,
): @Composable () -> T = {
    this.factory()
}
