package com.mrsanglier.tsumegohero.game.game

import androidx.compose.runtime.Immutable
import com.mrsanglier.tsumegohero.game.game.composable.CropBoard
import com.mrsanglier.tsumegohero.game.model.Cell

@Immutable
data class GameViewModelState(
    val whiteStones: Set<Cell> = emptySet(),
    val blackStones: Set<Cell> = emptySet(),
    val cropBoard: CropBoard? = null,
)
