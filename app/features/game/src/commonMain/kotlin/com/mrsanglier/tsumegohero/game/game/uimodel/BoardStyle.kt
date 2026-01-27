package com.mrsanglier.tsumegohero.game.game.uimodel

import androidx.compose.ui.graphics.Color
import com.mrsanglier.tsumegohero.app.coreui.resources.black34
import com.mrsanglier.tsumegohero.app.coreui.resources.texture2
import com.mrsanglier.tsumegohero.app.coreui.resources.white34
import com.mrsanglier.tsumegohero.coreui.resources.THDrawable
import org.jetbrains.compose.resources.DrawableResource

enum class BoardStyle(
    val backgroundRes: DrawableResource,
    val gridColor: Color,
    val blackStoneRes: DrawableResource,
    val whiteStoneRes: DrawableResource,
) {
    Default(
        backgroundRes = THDrawable.texture2,
        gridColor = Color.Black,
        blackStoneRes = THDrawable.black34,
        whiteStoneRes = THDrawable.white34,
    );
}