package com.mrsanglier.tsumegohero.game.game.composable

import com.mrsanglier.tsumegohero.game.game.Frame

data class CropBoard(
    val frame: Frame,
    val corner: Corner,
)

enum class Corner {
    TopLeft, TopRight, BottomLeft, BottomRight;
}