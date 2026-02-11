package com.mrsanglier.tsumegohero.game.delegate

import com.mrsanglier.tsumegohero.game.model.Board
import com.mrsanglier.tsumegohero.game.model.Cell
import com.mrsanglier.tsumegohero.game.model.Move
import com.mrsanglier.tsumegohero.game.model.Stone.Companion.getOpponent
import com.mrsanglier.tsumegohero.game.utils.zobristHash

interface PlayMoveDelegate {

    /** @return: Board updated, null if illegal move (no change applied) */
    fun playMoveOnBoard(
        previousBoard: Board,
        move: Move,
    ): Board?
}

class PlayMoveDelegateImpl() : PlayMoveDelegate {

    override fun playMoveOnBoard(
        previousBoard: Board,
        move: Move,
    ): Board? {
        val cell = move.gameMove
        val (x, y) = cell
        val stone = move.stone

        previousBoard.copy().apply {
            if (getStoneAt(cell) != null) return null

            grid[y][x] = stone

            // Capture opponent groups
            val captured = mutableSetOf<Cell>()
            neighbors(cell)
                .filter { neighbor ->
                    getStoneAt(neighbor) == stone.getOpponent()
                }
                .forEach { opponentNeighbor ->
                    val group = group(opponentNeighbor)
                    if (liberties(group).isEmpty()) {
                        captured.addAll(group)
                        group.forEach { (xCaptured, yCaptured) ->
                            grid[yCaptured][xCaptured] = null
                        }
                    }
                }

            val allCapturedStone = this.capturedStone.toMutableMap()
            if (captured.isNotEmpty()) {
                allCapturedStone[moveIndex] = captured
            }

            // Suicide check
            val liberties = liberties(group(cell))
            if (liberties.isEmpty()) {
                return null
            }

            // Simple ko check
            val newHash = grid.zobristHash(boardSize)
            val currentHash = previousBoard.grid.zobristHash(boardSize)

            if (previousHash != null && previousHash == newHash) {
                return null
            }
            return copy(
                previousHash = currentHash,
                moveIndex = moveIndex + 1,
                capturedStone = allCapturedStone,
            )
        }
    }
}

private fun Board.neighbors(cell: Cell): List<Cell> =
    listOf(
        Cell(cell.x - 1, cell.y),
        Cell(cell.x + 1, cell.y),
        Cell(cell.x, cell.y - 1),
        Cell(cell.x, cell.y + 1)
    ).filter { onBoard(it) }

private fun Board.group(start: Cell): Set<Cell> {
    val color = getStoneAt(start) ?: return emptySet()
    val visited = mutableSetOf<Cell>()
    val stack = ArrayDeque<Cell>()

    stack.add(start)

    while (stack.isNotEmpty()) {
        val cell = stack.removeLast()
        if (!visited.add(cell)) continue
        neighbors(cell)
            .filter { getStoneAt(it) == color }
            .forEach { stack.add(it) }
    }
    return visited
}

private fun Board.liberties(group: Set<Cell>): Set<Cell> =
    group.flatMap { neighbors(it) }
        .filter { getStoneAt(it) == null }
        .toSet()
