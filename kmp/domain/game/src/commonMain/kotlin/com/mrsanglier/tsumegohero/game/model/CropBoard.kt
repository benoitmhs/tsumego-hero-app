package com.mrsanglier.tsumegohero.game.model

data class CropBoard(
    val boardSection: BoardSection,
    val corner: Corner,
)

enum class Corner {
    TopLeft, TopRight, BottomLeft, BottomRight;
}