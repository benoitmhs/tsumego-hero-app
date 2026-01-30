package com.mrsanglier.tsumegohero.game.model

data class Tsumego(
    val initialBoard: Board,
    val root: RootNode,
) {
    val playerStone: Stone
        get() = root.children.first().move.stone
}
