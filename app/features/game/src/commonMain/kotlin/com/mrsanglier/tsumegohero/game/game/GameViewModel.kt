package com.mrsanglier.tsumegohero.game.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrsanglier.tsumegohero.game.game.composable.Corner
import com.mrsanglier.tsumegohero.game.game.composable.CropBoard
import com.mrsanglier.tsumegohero.game.model.Board
import com.mrsanglier.tsumegohero.game.model.BoardSize
import com.mrsanglier.tsumegohero.game.model.Cell
import com.mrsanglier.tsumegohero.game.model.Stone
import com.mrsanglier.tsumegohero.game.model.Stone.Companion.getOpponent
import com.mrsanglier.tsumegohero.game.usecase.PlayMoveUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class GameViewModel(
    private val playMoveUseCase: PlayMoveUseCase,
) : ViewModel() {

    private val board = MutableStateFlow(Board(BoardSize.X19))
    private val nextStone = MutableStateFlow(Stone.BLACK)

    val uiState: StateFlow<GameViewModelState>
        get() = board.map { board ->
            GameViewModelState(
                whiteStones = board.whiteStones,
                blackStones = board.blackStones,
                cropBoard = CropBoard(
                    cellRef = Cell(7, 6),
                    corner = Corner.TopLeft,
                ),
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), GameViewModelState())

    fun onClickCell(cell: Cell) {
        playMoveUseCase(
            previousBoard = board.value,
            cell = cell,
            stone = nextStone.value,
        )?.let { newBoard ->
            nextStone.update { it.getOpponent() }
            board.value = newBoard
        }
    }
}
