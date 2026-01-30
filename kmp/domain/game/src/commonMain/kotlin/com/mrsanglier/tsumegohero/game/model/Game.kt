package com.mrsanglier.tsumegohero.game.model

data class Game(
    val board: Board,
    val moveStack: List<MoveNode> = emptyList(),
    val tsumego: Tsumego,
    val cropBoard: CropBoard,
) {
    val playerStone: Stone
        get() = tsumego.playerStone

    val lastMove: MoveNode?
        get() = moveStack.lastOrNull()

    val isOpponentTurn: Boolean
        get() = lastMove?.move?.stone == playerStone

    val outcome: SgfNodeOutcome
        get() = lastMove?.outcome ?: SgfNodeOutcome.NONE
}
