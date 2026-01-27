package com.mrsanglier.tsumegohero.game.game.composable

import com.mrsanglier.tsumegohero.data.model.BoardSize
import com.mrsanglier.tsumegohero.data.model.Cell

data class CropBoard(
    val cellRef: Cell,
    val corner: Corner,
)

enum class Corner {
    TopLeft, TopRight, BottomLeft, BottomRight;
}