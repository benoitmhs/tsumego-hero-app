package com.mrsanglier.tsumegohero.coreui.extension

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.mrsanglier.tsumegohero.coreui.componants.background.BackgroundSpec

fun Color.asBackground(): BackgroundSpec = BackgroundSpec.Solid(this)
fun Brush.asBackground(): BackgroundSpec = BackgroundSpec.Gradient(this)
