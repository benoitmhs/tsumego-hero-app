package com.mrsanglier.tsumegohero.game.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mrsanglier.tsumegohero.coreui.componants.screen.THScreen
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

    }
}
