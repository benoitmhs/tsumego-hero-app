package com.mrsanglier.tsumegohero.game.usecase

import com.mrsanglier.tsumegohero.game.delegate.PlayMoveDelegate
import com.mrsanglier.tsumegohero.game.delegate.PlayMoveDelegateImpl
import com.mrsanglier.tsumegohero.game.model.Cell
import com.mrsanglier.tsumegohero.game.model.Game
import com.mrsanglier.tsumegohero.game.model.Move
import com.mrsanglier.tsumegohero.game.model.MoveNode
import com.mrsanglier.tsumegohero.game.model.SgfNodeOutcome
import com.mrsanglier.tsumegohero.game.model.Stone.Companion.getOpponent

class PlayReviewMoveUseCase(
    playMoveDelegateImpl: PlayMoveDelegateImpl,
) : PlayMoveDelegate by playMoveDelegateImpl {

    operator fun invoke(
        game: Game,
        cell: Cell,
    ): Game? {
        val currentNodes = game.lastMove?.children
            ?: game.reviewRoot?.children
            ?: return game

        val stone = game.lastMove?.move?.stone?.getOpponent()
            ?: game.playerStone

        val move = Move(stone = stone, gameMove = cell)
        val node = currentNodes.firstOrNull { it.move == move }
            ?: MoveNode(
                move = move,
                outcome = SgfNodeOutcome.FAILURE,
                children = mutableListOf(),
            )

        return playMoveOnBoard(game.board, move)?.let { board ->
            game.copy(
                board = board,
                moveStack = game.moveStack + listOf(node),
            )
        }
    }
}