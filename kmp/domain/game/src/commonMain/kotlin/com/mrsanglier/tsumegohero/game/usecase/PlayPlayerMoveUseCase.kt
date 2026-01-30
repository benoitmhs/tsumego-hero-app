package com.mrsanglier.tsumegohero.game.usecase

import com.mrsanglier.tsumegohero.core.error.THGameError
import com.mrsanglier.tsumegohero.core.error.toError
import com.mrsanglier.tsumegohero.core.result.THResult
import com.mrsanglier.tsumegohero.game.delegate.PlayMoveDelegate
import com.mrsanglier.tsumegohero.game.delegate.PlayMoveDelegateImpl
import com.mrsanglier.tsumegohero.game.model.Cell
import com.mrsanglier.tsumegohero.game.model.Game
import com.mrsanglier.tsumegohero.game.model.Move
import com.mrsanglier.tsumegohero.game.model.MoveNode
import com.mrsanglier.tsumegohero.game.model.SgfNodeOutcome

class PlayPlayerMoveUseCase(
    playMoveDelegateImpl: PlayMoveDelegateImpl,
) : PlayMoveDelegate by playMoveDelegateImpl {

    operator fun invoke(
        game: Game,
        cell: Cell,
    ): THResult<Game> = THResult.catchResult {
        val playerMove = Move(stone = game.playerStone, gameMove = cell)

        // Check is player’s turn
        if (game.playerStone == game.lastMove?.move?.stone) throw THGameError.Code.WrongPlayerTurn.toError()

        // Play move on board, check validity
        val boardUpdated = playMoveOnBoard(
            previousBoard = game.board,
            move = playerMove,
        ) ?: throw THGameError.Code.InvalidMove.toError()

        // Check tsumego outcome
        val possibleNodes = (game.lastMove?.children ?: game.tsumego.root.children)
        val currentNode = possibleNodes.firstOrNull { (_, move, _) ->
            move == playerMove
        } ?: MoveNode(
            children = mutableListOf(),
            move = playerMove,
            outcome = SgfNodeOutcome.FAILURE,
        )

        return@catchResult game.copy(
            board = boardUpdated,
            moveStack = game.moveStack + listOf(currentNode),
        )
    }
}