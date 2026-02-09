package com.mrsanglier.tsumegohero.game.usecase

import com.mrsanglier.tsumegohero.core.extension.circular
import com.mrsanglier.tsumegohero.core.result.THResult
import com.mrsanglier.tsumegohero.repository.TsumegoRepository
import kotlinx.coroutines.flow.first

class GetNextTsumegoIdUseCase(
    private val tsumegoRepository: TsumegoRepository,
) {
    suspend operator fun invoke(
        currentTsumegoId: String,
        isPrevious: Boolean,
    ): THResult<String> = THResult.catchResult {
        val sgf = tsumegoRepository.observeAllGames()
            .first()
            .sortedByDescending { it.updatedAt }
        val currentTsumegoIndex = sgf.indexOfFirst { tsumego -> tsumego.id == currentTsumegoId }

        val step = if (isPrevious) -1 else 1
        val nextIndex = (currentTsumegoIndex + step).circular(sgf.size)

        return@catchResult sgf[nextIndex].id
    }
}