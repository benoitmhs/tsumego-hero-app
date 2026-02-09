package com.mrsanglier.tsumegohero.dashboardgame.usecase

import com.mrsanglier.tsumegohero.core.result.THResult
import com.mrsanglier.tsumegohero.data.model.game.RawTsumego
import com.mrsanglier.tsumegohero.repository.TsumegoRepository
import kotlin.time.Clock

class ImportTsumegoUseCase(
    private val repository: TsumegoRepository,
) {
    suspend operator fun invoke(
        rawData: Map<String, String>,
    ): THResult<List<String>> = THResult.Companion.catchResult {
        val rawTsumegos = rawData.map { (name, data) ->
            RawTsumego(
                id = data.hashCode().toString(),
                name = name,
                data = data,
                updatedAt = Clock.System.now(),
            )
        }

        repository.upsert(rawTsumegos)

        return@catchResult rawTsumegos.map { it.id }
    }
}