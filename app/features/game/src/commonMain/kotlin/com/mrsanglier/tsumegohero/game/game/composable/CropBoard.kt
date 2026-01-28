package com.mrsanglier.tsumegohero.game.game.composable

import com.mrsanglier.tsumegohero.game.model.Cell

data class CropBoard(
    val cellRef: Cell,
    val corner: Corner,
)

enum class Corner {
    TopLeft, TopRight, BottomLeft, BottomRight;
}