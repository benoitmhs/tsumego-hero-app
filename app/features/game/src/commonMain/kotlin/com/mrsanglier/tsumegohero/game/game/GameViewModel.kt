package com.mrsanglier.tsumegohero.game.game

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrsanglier.tsumegohero.app.coreui.resources.ic_arrow_forward
import com.mrsanglier.tsumegohero.core.error.THGameError
import com.mrsanglier.tsumegohero.core.extension.handleResult
import com.mrsanglier.tsumegohero.coreui.componants.button.ButtonStyle
import com.mrsanglier.tsumegohero.coreui.componants.button.THButtonState
import com.mrsanglier.tsumegohero.coreui.componants.snackbar.SnackbarManager
import com.mrsanglier.tsumegohero.coreui.componants.snackbar.showError
import com.mrsanglier.tsumegohero.coreui.extension.composed
import com.mrsanglier.tsumegohero.coreui.extension.toIconSpec
import com.mrsanglier.tsumegohero.coreui.extension.toTextSpec
import com.mrsanglier.tsumegohero.coreui.resources.THDrawable
import com.mrsanglier.tsumegohero.coreui.theme.THTheme
import com.mrsanglier.tsumegohero.game.model.Cell
import com.mrsanglier.tsumegohero.game.model.Game
import com.mrsanglier.tsumegohero.game.model.SgfNodeOutcome
import com.mrsanglier.tsumegohero.game.model.Stone
import com.mrsanglier.tsumegohero.game.usecase.PlayOpponentMoveUseCase
import com.mrsanglier.tsumegohero.game.usecase.PlayPlayerMoveUseCase
import com.mrsanglier.tsumegohero.game.usecase.RestartGameUseCase
import com.mrsanglier.tsumegohero.game.usecase.StartGameUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

private val OPPONENT_TURN_DELAY: Duration = 300.milliseconds

class GameViewModel(
    private val playPlayerMoveUseCase: PlayPlayerMoveUseCase,
    private val playOpponentMoveUseCase: PlayOpponentMoveUseCase,
    private val startGameUseCase: StartGameUseCase,
    private val restartGameUseCase: RestartGameUseCase,
    private val snackbarManager: SnackbarManager,
) : ViewModel() {

    private val gameFlow = MutableStateFlow<Game?>(null)
    private val lockTouch = MutableStateFlow(false)
    private val indexCollection = MutableStateFlow(2)

    init {

        viewModelScope.launch {
            indexCollection.collect { i ->
                loadTsumego(i)
            }
        }

        viewModelScope.launch {
            gameFlow.collect { data ->
                lockTouch.emit(data?.isOpponentTurn == true)
                if (data?.isOpponentTurn == true) {
                    playOpponentTurn(data)
                }
            }
        }
    }

    val uiState: StateFlow<GameViewModelState>
        get() = gameFlow.map { game ->
            if (game == null) return@map initialState()
            val outcome = game.lastMove?.outcome ?: SgfNodeOutcome.NONE
            GameViewModelState(
                whiteStones = game.board.whiteStones,
                blackStones = game.board.blackStones,
                cropBoard = game.cropBoard,
                playerStone = when (game.playerStone) {
                    Stone.BLACK -> "Black to play".toTextSpec() // TODO: loco
                    Stone.WHITE -> "White to play".toTextSpec() // TODO: loco
                },
                result = when (outcome) {
                    SgfNodeOutcome.NONE -> null
                    SgfNodeOutcome.SUCCESS -> "✅ Correct".toTextSpec()
                    SgfNodeOutcome.FAILURE -> "❌ Incorrect".toTextSpec()
                },
                borderColor = THTheme.composed {
                    when (outcome) {
                        SgfNodeOutcome.NONE -> Color.Transparent
                        SgfNodeOutcome.SUCCESS -> colors.strokeSuccess
                        SgfNodeOutcome.FAILURE -> colors.strokeCritical
                    }
                },
                nextButton = defaultNextButton.copy(
                    style = if (outcome == SgfNodeOutcome.SUCCESS) {
                        ButtonStyle.Primary
                    } else ButtonStyle.Secondary,
                    text = if (outcome == SgfNodeOutcome.SUCCESS) {
                        "Next".toTextSpec()
                    } else null,
                ),
                resetButton = defaultRestartButton.copy(
                    style = if (game.lastMove?.outcome == SgfNodeOutcome.FAILURE) {
                        ButtonStyle.Primary
                    } else ButtonStyle.Secondary,
                ),
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), initialState())

    fun onClickCell(cell: Cell) {
        val game = gameFlow.value ?: return
        if (!lockTouch.value) {
            lockTouch.value = true

            playPlayerMoveUseCase(game, cell).handleResult(
                onSuccess = { data ->
                    gameFlow.value = data
                },
                onFailure = { error ->
                    when (error?.code) {
                        THGameError.Code.InvalidMove, THGameError.Code.WrongPlayerTurn -> Unit

                        else -> snackbarManager.showError(error)
                    }
                    lockTouch.value = false
                },
            )
        }
    }

    fun next() {
        indexCollection.update { it + 1 }
    }

    fun previous() {
        indexCollection.update { it - 1 }
    }

    fun reset() {
        gameFlow.update { game ->
            game?.let(restartGameUseCase::invoke)
        }
    }

    private fun initialState(): GameViewModelState = GameViewModelState(
        borderColor = { Color.Transparent },
        nextButton = defaultNextButton,
        resetButton = defaultRestartButton,
    )

    private fun loadTsumego(index: Int) {
        val result = startGameUseCase(index)
        result.data?.let { data ->
            gameFlow.value = data
        }
    }

    private suspend fun playOpponentTurn(game: Game) {

        game.lastMove?.children?.randomOrNull()?.let { opponentNode ->

            playOpponentMoveUseCase(game).handleResult(onSuccess = { data ->
                if (data != null) {
                    delay(OPPONENT_TURN_DELAY)
                    gameFlow.emit(data)
                }
            }, onFailure = { error ->
                snackbarManager.showError(error)
            })
        }
    }

    private val defaultRestartButton: THButtonState
        get() = THButtonState(
            text = "Restart".toTextSpec(), // TODO: loco
            style = ButtonStyle.Secondary,
            onClick = ::reset,
        )

    private val defaultNextButton: THButtonState
        get() = THButtonState(
            text = null,
            trailingIcon = THDrawable.ic_arrow_forward.toIconSpec(),
            style = ButtonStyle.Secondary,
            onClick = ::next,
        )
}
