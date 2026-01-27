package com.mrsanglier.tsumegohero.game.game.uimodel

import androidx.compose.ui.graphics.Color
import com.mrsanglier.tsumegohero.app.coreui.resources.texture2
import com.mrsanglier.tsumegohero.coreui.resources.THDrawable
import org.jetbrains.compose.resources.DrawableResource

enum class BoardStyle(
    val backgroundRes: DrawableResource,
    val gridColor: Color,
) {
    Default(THDrawable.texture2, Color.Black);
}