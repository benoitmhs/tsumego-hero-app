package com.mrsanglier.tsumegohero.game.usecase

import com.mrsanglier.tsumegohero.core.error.THGameError
import com.mrsanglier.tsumegohero.core.error.toError
import com.mrsanglier.tsumegohero.core.result.THResult
import com.mrsanglier.tsumegohero.game.model.Board
import com.mrsanglier.tsumegohero.game.model.BoardSize
import com.mrsanglier.tsumegohero.game.model.Stone
import com.mrsanglier.tsumegohero.game.model.Tsumego
import com.mrsanglier.tsumegohero.game.utils.SgfParser.parseInitialStones
import com.mrsanglier.tsumegohero.game.utils.SgfParser.parseTree
import com.mrsanglier.tsumegohero.game.utils.SgfParser.tokenize

class LoadTsumegoUseCase {
    operator fun invoke(sgf: String): THResult<Tsumego> = THResult.catchResult {
        val cleaned = sgf.replace(Regex("\\s"), "")

        val size = Regex("SZ\\[(\\d+)]")
            .find(cleaned)?.groupValues?.get(1)?.toInt() ?: 19
        val boardSize = BoardSize.fromValue(size)
            ?: throw THGameError.Code.SgfFormatNotSupported.toError("Invalid board size", "Board size: $size")

        val board = Board(boardSize)

        parseInitialStones(cleaned, "AB").forEach { cell ->
            board.setupStone(cell, Stone.BLACK)
        }
        parseInitialStones(cleaned, "AW").forEach { cell ->
            board.setupStone(cell, Stone.WHITE)
        }

        val tokens = tokenize(cleaned).drop(2)
        val root = parseTree(tokens)

        return@catchResult Tsumego(
            initialBoard = board,
            board = board,
            root = root,
        )
    }

}