package com.mrsanglier.tsumegohero.game.usecase

import com.mrsanglier.tsumegohero.core.error.THGameError
import com.mrsanglier.tsumegohero.core.error.toError
import com.mrsanglier.tsumegohero.core.result.THResult
import com.mrsanglier.tsumegohero.game.delegate.PlayMoveDelegate
import com.mrsanglier.tsumegohero.game.delegate.PlayMoveDelegateImpl
import com.mrsanglier.tsumegohero.game.model.Game

class PlayOpponentMoveUseCase(
    playMoveDelegateImpl: PlayMoveDelegateImpl,
) : PlayMoveDelegate by playMoveDelegateImpl {

    /** @return return new game config after opponent turn, null if there is no opponent turn */
    operator fun invoke(game: Game): THResult<Game?> = THResult.catchResult {

        // Check is opponent’s turn
        if (game.playerStone != game.lastMove?.move?.stone) throw THGameError.Code.WrongPlayerTurn.toError()

        val opponentNode = game.lastMove?.children?.randomOrNull() ?: return@catchResult null

        val boardUpdated = playMoveOnBoard(
            previousBoard = game.board,
            move = opponentNode.move,
        ) ?: throw THGameError.Code.InvalidMove.toError()


        return@catchResult game.copy(
            board = boardUpdated,
            moveStack = game.moveStack + listOf(opponentNode),
        )
    }
}