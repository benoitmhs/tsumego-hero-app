package com.mrsanglier.tsumegohero.game.game

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mrsanglier.tsumegohero.app.coreui.resources.ic_arrow_back
import com.mrsanglier.tsumegohero.coreui.componants.button.ButtonStyle
import com.mrsanglier.tsumegohero.coreui.componants.button.THButton
import com.mrsanglier.tsumegohero.coreui.componants.screen.THScreen
import com.mrsanglier.tsumegohero.coreui.componants.text.THText
import com.mrsanglier.tsumegohero.coreui.extension.toIconSpec
import com.mrsanglier.tsumegohero.coreui.extension.toTextSpec
import com.mrsanglier.tsumegohero.coreui.resources.THDrawable
import com.mrsanglier.tsumegohero.coreui.theme.THTheme
import com.mrsanglier.tsumegohero.game.game.composable.Board
import com.mrsanglier.tsumegohero.game.model.Corner
import com.mrsanglier.tsumegohero.game.model.Cell
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GameRoute(
    navScope: GameNavScope,
    viewModel: GameViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    GameScreen(
        uiState = uiState,
        onClickCell = viewModel::onClickCell,
        onClickPrevious = viewModel::previous,
    )
}

@Composable
private fun GameScreen(
    uiState: GameViewModelState,
    onClickCell: (Cell) -> Unit,
    onClickPrevious: () -> Unit,
) {
    THScreen { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp, alignment = Alignment.CenterVertically),
            ) {
                THText(
                    text = uiState.playerStone ?: "".toTextSpec(),
                    style = THTheme.typography.title200
                )

                Board(
                    whiteStones = uiState.whiteStones,
                    blackStones = uiState.blackStones,
                    cropBoard = uiState.cropBoard,
                    onClickCell = onClickCell,
                    lastMove = uiState.lastMove,
                    modifier = Modifier
                        .border(
                            width = THTheme.stroke.regular,
                            color = uiState.borderColor(),
                            shape = THTheme.shape.roundSmall,
                        )
                )
                THText(
                    text = uiState.result ?: "".toTextSpec(),
                    style = THTheme.typography.title200,
                )
            }

            Row(
                modifier = Modifier.padding(THTheme.spacing.large),
                horizontalArrangement = Arrangement.spacedBy(THTheme.spacing.large),
            ) {

                THButton(
                    icon = THDrawable.ic_arrow_back.toIconSpec(),
                    style = ButtonStyle.Secondary,
                    text = null,
                    onClick = onClickPrevious,
                )

                uiState.resetButton.Content(
                    modifier = Modifier
                        .weight(1f)
                )

                uiState.nextButton.Content(
                    modifier = Modifier.animateContentSize()
                )
            }
        }
    }
}

private fun Set<Cell>.rotate(corner: Corner, boardSize: Int): Set<Cell> {
    return this.map {
        it.rotate(corner, boardSize)
    }
        .toSet()
}

private fun Cell.rotate(corner: Corner, boardSize: Int): Cell {
    return when (corner) {
        Corner.TopLeft -> this
        Corner.BottomLeft -> Cell(x = y, y = boardSize - x)
        Corner.TopRight -> Cell(x = boardSize - y, y = x)
        Corner.BottomRight -> Cell(x = boardSize - x, boardSize - y)
    }
}