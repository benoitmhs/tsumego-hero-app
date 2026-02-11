package com.mrsanglier.tsumegohero.game.usecase

import com.mrsanglier.tsumegohero.game.model.Game
import com.mrsanglier.tsumegohero.game.model.MoveNode
import com.mrsanglier.tsumegohero.game.model.SgfNodeOutcome

class StartReviewUseCase {
    operator fun invoke(game: Game): Game {

        return game.copy(
            board = game.tsumego.initialBoard,
            moveStack = emptyList(),
            reviewRoot = game.tsumego.root.copy(
                children = game.tsumego.root.children
                    .map { it.toReviewNode() }
                    .toMutableList(),
            ),
            reviewIndex = 0,
        )
    }

    private fun MoveNode.toReviewNode(): MoveNode {
        val reviewChildren = children.map { it.toReviewNode() }
        val outcome = when {
            reviewChildren.isEmpty() -> outcome
            reviewChildren.any { it.outcome == SgfNodeOutcome.SUCCESS } -> SgfNodeOutcome.SUCCESS
            else -> SgfNodeOutcome.FAILURE
        }

        return this.copy(
            children = reviewChildren.toMutableList(),
            outcome = outcome,
        )
    }
}
