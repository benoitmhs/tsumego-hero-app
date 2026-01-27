package com.mrsanglier.tsumegohero.game.game

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mrsanglier.tsumegohero.coreui.componants.screen.THScreen
import com.mrsanglier.tsumegohero.data.model.Cell
import com.mrsanglier.tsumegohero.game.game.composable.Board
import com.mrsanglier.tsumegohero.game.game.composable.Corner
import com.mrsanglier.tsumegohero.game.game.composable.CropBoard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameRoute(
    navScope: GameNavScope,
    viewModel: GameViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GameScreen(
        uiState = uiState,
    )
}

@Composable
private fun GameScreen(
    uiState: GameViewModelState,
) {
    THScreen {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Board(
                whiteStones = setOf(
                    Cell(0, 4),
                    Cell(1, 5),
                    Cell(2, 5),
                    Cell(2, 3),
                    Cell(3, 4),
                    Cell(4, 4),
                    Cell(4, 3),
                    Cell(4, 2),
                ),
                blackStones = setOf(
                    Cell(0, 3),
                    Cell(1, 1),
                    Cell(1, 3),
                    Cell(1, 4),
                    Cell(2, 4),
                    Cell(3, 3),
                    Cell(3, 2),
                ),
                cropBoard = CropBoard(
                    Cell(x = 15, y = 4),
                    corner = Corner.TopLeft,
                ),
            )
        }
    }
}
