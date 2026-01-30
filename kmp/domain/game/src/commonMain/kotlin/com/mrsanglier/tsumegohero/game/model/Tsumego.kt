package com.mrsanglier.tsumegohero.game.model

data class Tsumego(
    val initialBoard: Board,
    val board: Board,
    val root: RootNode,
    val moveStack: List<MoveNode> = emptyList(),
) {
    val playerStone: Stone
        get() = root.children.first().move.stone

    val lastMove: MoveNode?
        get() = moveStack.lastOrNull()

    val isOpponentTurn: Boolean
        get() = lastMove?.move?.stone == playerStone
}
