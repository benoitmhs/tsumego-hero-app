package com.mrsanglier.tsumegohero.coreui.componants.spacer

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun Dp.THHorizontalSpacer() {
    Spacer(modifier = Modifier.width(this))
}

@Composable
fun Dp.THVerticalSpacer() {
    Spacer(modifier = Modifier.height(this))
}

@Composable
fun RowScope.THSpacerWeight() {
    Spacer(modifier = Modifier.weight(1f))
}

@Composable
fun ColumnScope.THSpacerWeight(
    @FloatRange(from = 0.0, fromInclusive = false) weight: Float = 1f,
) {
    Spacer(modifier = Modifier.weight(weight))
}
