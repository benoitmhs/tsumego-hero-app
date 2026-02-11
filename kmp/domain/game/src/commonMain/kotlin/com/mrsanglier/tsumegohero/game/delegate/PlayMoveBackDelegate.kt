package com.mrsanglier.tsumegohero.game.delegate

import com.mrsanglier.tsumegohero.game.model.Board
import com.mrsanglier.tsumegohero.game.model.Grid
import com.mrsanglier.tsumegohero.game.model.Move
import com.mrsanglier.tsumegohero.game.model.Stone.Companion.getOpponent
import com.mrsanglier.tsumegohero.game.utils.copy
import com.mrsanglier.tsumegohero.game.utils.zobristHash
import kotlin.collections.forEach
import kotlin.collections.orEmpty

interface PlayMoveBackDelegate {
    fun playMoveBackOnBoard(
        board: Board,
        lastMove: Move,
        preLastMove: Move?,
    ): Board
}

class PlayMoveBackDelegateImpl : PlayMoveBackDelegate {
    override fun playMoveBackOnBoard(
        board: Board,
        lastMove: Move,
        preLastMove: Move?,
    ): Board {
        val previousGrid = board.getPreviousGrid(lastMove)
        val previousCapturedStones = board.capturedStone.toMutableMap().apply {
            remove(board.moveIndex)
        }
        val previousBoard = board.copy(
            grid = previousGrid,
            moveIndex = (board.moveIndex - 1).coerceAtLeast(0),
            capturedStone = previousCapturedStones,
        )
        val previousHash = preLastMove?.let { move ->
            previousBoard.getPreviousGrid(move)
                .zobristHash(previousBoard.boardSize)
        }

        return previousBoard.copy(previousHash = previousHash)
    }

    private fun Board.getPreviousGrid(lastMove: Move): Grid {
        val opponentStone = lastMove.stone.getOpponent()
        val capturedStones = capturedStone.toMutableMap()
        val stoneToPutBack = capturedStones.remove(moveIndex - 1).orEmpty()

        val previousGrid = grid.copy()
        // Remove last move
        previousGrid[lastMove.gameMove.y][lastMove.gameMove.x] = null

        // Reput captured stones
        stoneToPutBack.forEach { (x, y) ->
            previousGrid[y][x] = opponentStone
        }

        return previousGrid
    }
}