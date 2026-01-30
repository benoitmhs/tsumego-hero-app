package com.mrsanglier.tsumegohero.game.model

sealed interface SgfNode {
    val children: MutableList<MoveNode>
}

data class MoveNode(
    override val children: MutableList<MoveNode>,
    val move: Move,
    var outcome: SgfNodeOutcome,
) : SgfNode

data class RootNode(
    override val children: MutableList<MoveNode> = mutableListOf(),
) : SgfNode