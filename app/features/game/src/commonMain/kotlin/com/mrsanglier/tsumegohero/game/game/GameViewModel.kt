package com.mrsanglier.tsumegohero.game.game

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrsanglier.tsumegohero.app.coreui.resources.ic_arrow_forward
import com.mrsanglier.tsumegohero.coreui.componants.button.ButtonStyle
import com.mrsanglier.tsumegohero.coreui.componants.button.THButtonState
import com.mrsanglier.tsumegohero.coreui.extension.composed
import com.mrsanglier.tsumegohero.coreui.extension.toIconSpec
import com.mrsanglier.tsumegohero.coreui.extension.toTextSpec
import com.mrsanglier.tsumegohero.coreui.resources.THDrawable
import com.mrsanglier.tsumegohero.coreui.theme.THTheme
import com.mrsanglier.tsumegohero.game.game.composable.Corner
import com.mrsanglier.tsumegohero.game.game.composable.CropBoard
import com.mrsanglier.tsumegohero.game.model.Board
import com.mrsanglier.tsumegohero.game.model.BoardSize
import com.mrsanglier.tsumegohero.game.model.Cell
import com.mrsanglier.tsumegohero.game.model.Move
import com.mrsanglier.tsumegohero.game.model.MoveNode
import com.mrsanglier.tsumegohero.game.model.RootNode
import com.mrsanglier.tsumegohero.game.model.SgfNodeOutcome
import com.mrsanglier.tsumegohero.game.model.Stone
import com.mrsanglier.tsumegohero.game.model.Stone.Companion.getOpponent
import com.mrsanglier.tsumegohero.game.model.Tsumego
import com.mrsanglier.tsumegohero.game.usecase.LoadTsumegoUseCase
import com.mrsanglier.tsumegohero.game.usecase.PlayMoveUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt
import kotlin.time.Duration.Companion.seconds

class GameViewModel(
    private val playMoveUseCase: PlayMoveUseCase,
    private val loadTsumegoUseCase: LoadTsumegoUseCase,
) : ViewModel() {

    private val tsumego = MutableStateFlow<Tsumego?>(null)
    private val nextStone = MutableStateFlow(Stone.BLACK)
    private val lockTouch = MutableStateFlow(false)
    private val indexCollection = MutableStateFlow(2)

    init {

        viewModelScope.launch {
            indexCollection.collect { i ->
                loadTsumego(i)
            }
        }

        viewModelScope.launch {
            tsumego.collect { data ->
                lockTouch.value = data?.isOpponentTurn == true
                if (data?.isOpponentTurn == true) {
                    playOpponentTurn(data)
                }
            }
        }
    }

    val uiState: StateFlow<GameViewModelState>
        get() = tsumego.map { data ->
            if (data == null) return@map initialState()
            val outcome = data.lastMove?.outcome ?: SgfNodeOutcome.NONE
            GameViewModelState(
                whiteStones = data.board.whiteStones,
                blackStones = data.board.blackStones,
                cropBoard = getCropBoard(data.root, data.initialBoard),
                playerStone = when (data.playerStone) {
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
                    style = if (data.lastMove?.outcome == SgfNodeOutcome.FAILURE) {
                        ButtonStyle.Primary
                    } else ButtonStyle.Secondary,
                ),
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), initialState())

    fun onClickCell(cell: Cell) {
        tsumego.value?.let { data ->
            if (!lockTouch.value) {
                lockTouch.value = true
                playMoveUseCase(
                    previousBoard = data.board,
                    cell = cell,
                    stone = data.playerStone,
                )?.let { newBoard ->
                    val currentMove = Move(nextStone.value, cell)

                    val possibleNodes = (data.lastMove?.children ?: data.root.children)
                    val currentNode = possibleNodes.firstOrNull { (_, move, _) ->
                        move == currentMove
                    } ?: MoveNode(children = mutableListOf(), move = currentMove, outcome = SgfNodeOutcome.FAILURE)
                    tsumego.value = data.copy(
                        board = newBoard,
                        moveStack = data.moveStack + listOf(currentNode)
                    )
                }
            }
        }
    }

    fun next() {
        indexCollection.update { it + 1 }
    }

    fun previous() {
        indexCollection.update { it - 1 }
    }

    fun reset() {
        tsumego.update { data ->
            data?.copy(
                board = data.initialBoard,
                moveStack = emptyList(),
            )
        }
    }

    private fun initialState(): GameViewModelState = GameViewModelState(
        borderColor = { Color.Transparent },
        nextButton = defaultNextButton,
        resetButton = defaultRestartButton,
    )

    private fun loadTsumego(index: Int) {
        val i = index % sgfCollection.size
        val result = loadTsumegoUseCase(sgfCollection[i])
        result.data?.let { data ->
            tsumego.value = data
            nextStone.value = data.playerStone
        }
    }

    private suspend fun playOpponentTurn(data: Tsumego) {
        data.lastMove?.children?.randomOrNull()?.let { opponentNode ->
            delay(300)

            playMoveUseCase(
                previousBoard = data.board,
                cell = opponentNode.move.gameMove,
                stone = data.playerStone.getOpponent(),
            )?.let { newboard ->
                tsumego.emit(
                    data.copy(
                        board = newboard,
                        moveStack = data.moveStack + listOf(opponentNode),
                    )
                )
            } ?: println("Invalid move in sgf") // TODO: handle error
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

    private fun getCropBoard(root: RootNode, initialBoard: Board): CropBoard {
        val boardFrame = Frame(
            0,
            0,
            initialBoard.boardSize.size - 1,
            initialBoard.boardSize.size - 1,
        )

        val initCells = initialBoard.blackStones + initialBoard.whiteStones
        val initFrame = Frame(
            xMin = initCells.minOf { it.x },
            xMax = initCells.maxOf { it.x },
            yMin = initCells.minOf { it.y },
            yMax = initCells.maxOf { it.y },
        )
        val tsumegoFrame = getTsumegoFrame(root, initFrame)

        val cropCorner: Corner = Corner.entries
            .map { corner ->
                cellDistance(
                    boardFrame.getClosestCell(corner),
                    tsumegoFrame.getClosestCell(corner),
                ) to corner
            }
            .minBy { (distance, _) -> distance }
            .second

        val cornerCell = boardFrame.getClosestCell(cropCorner)
        val gameFrame = tsumegoFrame.updateFromCell(cornerCell)

        return CropBoard(frame = gameFrame, corner = cropCorner)
    }

    private fun getTsumegoFrame(root: RootNode, initFrame: Frame): Frame {
        var frame = initFrame

        root.children.forEach { child ->
            frame = getFrameNode(child, frame)
        }

        return frame
    }

    private fun getFrameNode(node: MoveNode, frame: Frame): Frame {
        var newFrame = frame.updateFromCell(node.move.gameMove)
        node.children.forEach { child ->
            newFrame = getFrameNode(child, newFrame)
        }

        return newFrame
    }

    private fun cellDistance(a: Cell, b: Cell): Double {
        val X = abs(a.x - b.x) * abs(a.x - b.x)
        val Y = abs(a.y - b.y) * abs(a.y - b.y)
        return sqrt(X.toDouble() + Y.toDouble())
    }

    private fun Frame.getClosestCell(corner: Corner): Cell = when (corner) {
        Corner.TopLeft -> Cell(xMin, yMin)
        Corner.TopRight -> Cell(xMax, yMin)
        Corner.BottomLeft -> Cell(xMin, yMax)
        Corner.BottomRight -> Cell(xMax, yMax)
    }
}

data class Frame(
    val xMin: Int,
    val yMin: Int,
    val xMax: Int,
    val yMax: Int,
) {
    constructor(cell: Cell) : this(
        xMin = cell.x,
        yMin = cell.y,
        xMax = cell.x,
        yMax = cell.y,
    )

    fun updateFromCell(cell: Cell): Frame = cell.let { (x, y) ->
        Frame(
            xMin = min(xMin, x),
            yMin = min(yMin, y),
            xMax = max(xMax, x),
            yMax = max(yMax, y),
        )
    }
}
