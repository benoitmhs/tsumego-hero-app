package com.mrsanglier.tsumegohero.coreui.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.PluralStringResource
import org.jetbrains.compose.resources.StringResource

fun StringResource.toTextSpec(vararg args: Any): TextSpec = TextSpec.StringRes(this, *args)
fun PluralStringResource.toTextSpec(count: Int, vararg args: Any): TextSpec = TextSpec.PluralsRes(this, count, *args)
fun DrawableResource.toIconSpec(
    tint: (@Composable () -> Color)? = null,
): IconSpec = IconSpec.Resources(
    drawableRes = this,
    contentDescription = null,
    tint = tint,
)