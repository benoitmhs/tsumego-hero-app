package com.mrsanglier.tsumegohero.data.model

data class Position(
    val boardSize: BoardSize,
    val blackStones: Set<Cell>,
    val whiteStones: Set<Cell>,
    val lastMove: GameMove?,
    val lastPlayerMove: StoneType?,
    val currentMoveIndex: Int,
)
