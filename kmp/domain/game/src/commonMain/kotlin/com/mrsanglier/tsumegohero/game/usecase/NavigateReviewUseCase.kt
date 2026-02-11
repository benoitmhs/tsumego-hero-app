package com.mrsanglier.tsumegohero.game.usecase

import com.mrsanglier.tsumegohero.game.delegate.PlayMoveBackDelegate
import com.mrsanglier.tsumegohero.game.delegate.PlayMoveBackDelegateImpl
import com.mrsanglier.tsumegohero.game.delegate.PlayMoveDelegate
import com.mrsanglier.tsumegohero.game.delegate.PlayMoveDelegateImpl
import com.mrsanglier.tsumegohero.game.model.Game

class NavigateReviewUseCase(
    playMoveDelegateImpl: PlayMoveDelegateImpl,
    playMoveBackDelegateImpl: PlayMoveBackDelegateImpl,
) : PlayMoveDelegate by playMoveDelegateImpl,
    PlayMoveBackDelegate by playMoveBackDelegateImpl {

    operator fun invoke(
        game: Game,
        isBack: Boolean,
    ): Game? {
        return when {
            isBack && game.reviewIndex > 0 -> {
                game.copy(
                    board = playMoveBackOnBoard(
                        board = game.board,
                        lastMove = game.moveStack[game.reviewIndex - 1].move,
                        preLastMove = game.moveStack.getOrNull(game.reviewIndex - 2)?.move,
                    ),
                    reviewIndex = game.reviewIndex - 1,
                )
            }

            !isBack -> {
                val nextNode = game.nextGoodMove.randomOrNull() ?: return null

                playMoveOnBoard(
                    previousBoard = game.board,
                    move = nextNode.move,
                )?.let { board ->
                    game.copy(
                        board = board,
                        reviewIndex = game.reviewIndex + 1,
                        moveStack = game.moveStack.dropLast(game.moveStack.size - game.reviewIndex) + listOf(nextNode),
                    )
                }
            }

            else -> null
        }
    }
}
