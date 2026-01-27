package com.mrsanglier.tsumegohero.data.model

interface GameMove

data object Pass : GameMove

data class Cell(
    val x: Int,
    val y: Int,
) : GameMove {
    fun getLeftNeighbour(boardSize: BoardSize): Cell? =
        Cell(x - 1, y).takeIf { it.isValid(boardSize) }

    fun getRightNeighbour(boardSize: BoardSize): Cell? =
        Cell(x + 1, y).takeIf { it.isValid(boardSize) }

    fun getTopNeighbour(boardSize: BoardSize): Cell? =
        Cell(x, y - 1).takeIf { it.isValid(boardSize) }

    fun getBottomNeighbour(boardSize: BoardSize): Cell? =
        Cell(x, y + 1).takeIf { it.isValid(boardSize) }

    private fun isValid(boardSize: BoardSize): Boolean =
        x in 0..<boardSize.size
            && y in 0..<boardSize.size
}
