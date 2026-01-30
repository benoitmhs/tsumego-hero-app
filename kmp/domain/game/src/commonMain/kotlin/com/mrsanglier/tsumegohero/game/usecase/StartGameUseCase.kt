package com.mrsanglier.tsumegohero.game.usecase

import com.mrsanglier.tsumegohero.core.result.THResult
import com.mrsanglier.tsumegohero.game.delegate.GetCropBoardDelegate
import com.mrsanglier.tsumegohero.game.delegate.GetCropBoardDelegateImpl
import com.mrsanglier.tsumegohero.game.delegate.ParseSgfTsumegoDelegate
import com.mrsanglier.tsumegohero.game.delegate.ParseSgfTsumegoDelegateImpl
import com.mrsanglier.tsumegohero.game.model.Game
import com.mrsanglier.tsumegohero.game.sgfCollection

class StartGameUseCase(
    parseSgfTsumegoDelegateImpl: ParseSgfTsumegoDelegateImpl,
    getCropBoardDelegateImpl: GetCropBoardDelegateImpl,
) : ParseSgfTsumegoDelegate by parseSgfTsumegoDelegateImpl,
    GetCropBoardDelegate by getCropBoardDelegateImpl {

    operator fun invoke(tsumegoIndex: Int): THResult<Game> = THResult.catchResult {
        val i = tsumegoIndex % sgfCollection.size
        val tsumego = parseSgfTsumego(sgfCollection[i])

        return@catchResult Game(
            tsumego = tsumego,
            board = tsumego.initialBoard,
            moveStack = emptyList(),
            cropBoard = getCropBoard(tsumego),
        )
    }
}