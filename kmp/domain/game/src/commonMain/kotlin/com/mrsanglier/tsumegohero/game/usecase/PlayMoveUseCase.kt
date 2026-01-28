package com.mrsanglier.tsumegohero.game.usecase

import com.mrsanglier.tsumegohero.game.model.Board
import com.mrsanglier.tsumegohero.game.model.Cell
import com.mrsanglier.tsumegohero.game.model.Stone
import com.mrsanglier.tsumegohero.game.model.Stone.Companion.getOpponent
import com.mrsanglier.tsumegohero.game.utils.zobristHash

class PlayMoveUseCase() {
    /**
     * @return: Board updated, null if illegal move (no change applied)
     */
    operator fun invoke(
        previousBoard: Board,
        cell: Cell,
        stone: Stone,
    ): Board? {
        val (x, y) = cell
        previousBoard.copy().apply {
            if (getStoneAt(cell) != null) return null

            grid[y][x] = stone

            // Capture opponent groups
            neighbors(cell)
                .filter { neighbor ->
                    getStoneAt(neighbor) == stone.getOpponent()
                }
                .forEach { opponentNeighbor ->
                    val group = group(opponentNeighbor)
                    if (liberties(group).isEmpty())
                        group.forEach { (xCaptured, yCaptured) ->
                            grid[yCaptured][xCaptured] = null
                        }
                }

            // Suicide check
            val liberties = liberties(group(cell))
            if (liberties.isEmpty()) {
                return null
            }

            // Simple ko check
            val newHash = zobristHash()
            val currentHash = previousBoard.zobristHash()

            if (previousHash != null && previousHash == newHash) {
                return null
            }
            return copy(previousHash = currentHash)
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
