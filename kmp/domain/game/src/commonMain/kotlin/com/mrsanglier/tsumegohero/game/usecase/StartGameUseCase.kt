package com.mrsanglier.tsumegohero.game.usecase

import com.mrsanglier.tsumegohero.core.result.THResult
import com.mrsanglier.tsumegohero.game.delegate.DeriveTsumegoDelegate
import com.mrsanglier.tsumegohero.game.delegate.DeriveTsumegoDelegateImpl
import com.mrsanglier.tsumegohero.game.delegate.GetCropBoardDelegate
import com.mrsanglier.tsumegohero.game.delegate.GetCropBoardDelegateImpl
import com.mrsanglier.tsumegohero.game.delegate.ParseSgfTsumegoDelegate
import com.mrsanglier.tsumegohero.game.delegate.ParseSgfTsumegoDelegateImpl
import com.mrsanglier.tsumegohero.game.model.Game
import com.mrsanglier.tsumegohero.game.sgfCollection

class StartGameUseCase(
    parseSgfTsumegoDelegateImpl: ParseSgfTsumegoDelegateImpl,
    getCropBoardDelegateImpl: GetCropBoardDelegateImpl,
    deriveTsumegoDelegateImpl: DeriveTsumegoDelegateImpl,
) : ParseSgfTsumegoDelegate by parseSgfTsumegoDelegateImpl,
    GetCropBoardDelegate by getCropBoardDelegateImpl,
    DeriveTsumegoDelegate by deriveTsumegoDelegateImpl {

    operator fun invoke(tsumegoIndex: Int): THResult<Game> = THResult.catchResult {
        val i = tsumegoIndex % sgfCollection.size
        val tsumego = parseSgfTsumego(sgfCollection[i])
        val derivedTsumego = deriveTsumego(tsumego)

        return@catchResult Game(
            tsumego = derivedTsumego,
            board = derivedTsumego.initialBoard,
            moveStack = emptyList(),
            cropBoard = getCropBoard(derivedTsumego),
        )
    }
}