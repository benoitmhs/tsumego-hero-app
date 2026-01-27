package com.mrsanglier.tsumegohero.game.game.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import com.mrsanglier.tsumegohero.coreui.theme.THTheme
import com.mrsanglier.tsumegohero.data.model.BoardSize
import com.mrsanglier.tsumegohero.game.game.uimodel.BoardStyle
import org.jetbrains.compose.resources.painterResource

@Composable
fun Board(
    boardSize: BoardSize = BoardSize.X19,
    modifier: Modifier = Modifier,
    style: BoardStyle = BoardStyle.Default,
    cropBoard: CropBoard? = null,
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(THTheme.shape.roundSmall),
    ) {
        Image(
            painter = painterResource(style.backgroundRes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .rotate(cropBoard?.corner.getRotation()),
        ) {
            drawBoardGrid2(
                boardSize = boardSize,
                cropBoard = cropBoard,
                style = style,
            )
        }
    }
}

private fun Corner?.getRotation(): Float =
    when (this) {
        Corner.TopLeft, null -> 0f
        Corner.TopRight -> 90f
        Corner.BottomLeft -> -90f
        Corner.BottomRight -> 180f
    }

private fun DrawScope.drawBoardGrid(
    boardSize: BoardSize,
    cropBoard: CropBoard?,
    style: BoardStyle,
) {
    val (boardWidth, boardHeight) = getBoardSize(boardSize, cropBoard)

    val cellSpacing = size.width / (boardWidth - 1 + getBorderSpacing(boardSize, cropBoard))
    val boarderSpacing = cellSpacing * BORDER_SPACING_COEF
    val cropSpacing = cellSpacing / 2f
    val lineStroke = LINE_STROKE_COEF / maxOf(boardWidth, boardHeight)

    // Draw column lines
    val columnOffset = when (cropBoard?.corner) {
        Corner.TopLeft, null -> Offset(x = boarderSpacing, y = boarderSpacing)
        Corner.TopRight -> Offset(x = cropSpacing, boarderSpacing)
        Corner.BottomLeft -> Offset(x = boarderSpacing, y = 0f)
        Corner.BottomRight -> Offset(x = cropSpacing, y = 0f)
    }
    val columnEndSpacing = when (cropBoard?.corner) {
        Corner.TopLeft, Corner.TopRight -> 0f
        Corner.BottomLeft, Corner.BottomRight, null -> boarderSpacing
    }

    for (x in 0..<boardWidth) {
        val startOffset = columnOffset + Offset(x = x * cellSpacing, y = 0f)
        val endOffset = startOffset.copy(y = this.size.height - columnEndSpacing)
        drawLine(
            color = style.gridColor,
            start = startOffset,
            end = endOffset,
            strokeWidth = lineStroke,
        )
    }

    // Draw row lines
    val rowOffset = when (cropBoard?.corner) {
        Corner.TopLeft, null -> Offset(x = boarderSpacing, y = boarderSpacing)
        Corner.TopRight -> Offset(x = 0f, y = boarderSpacing)
        Corner.BottomLeft -> Offset(x = boarderSpacing, y = cropSpacing)
        Corner.BottomRight -> Offset(x = 0f, y = cropSpacing)
    }
    val rowEndSpacing = when (cropBoard?.corner) {
        Corner.BottomLeft, Corner.TopLeft, null -> 0f
        Corner.BottomRight, Corner.TopRight, null -> boarderSpacing
    }

    for (y in 0..<boardHeight) {
        val startOffset = rowOffset + Offset(x = 0f, y = y * cellSpacing)
        val endOffset = startOffset.copy(x = this.size.width - rowEndSpacing)
        drawLine(
            color = style.gridColor,
            start = startOffset,
            end = endOffset,
            strokeWidth = lineStroke,
        )
    }

    // Draw circle markers

}

private fun getBoardRanges(boardSize: BoardSize, cropBoard: CropBoard?): Pair<IntRange, IntRange> {
    return when (cropBoard?.corner) {
        Corner.TopLeft -> 0..cropBoard.cellRef.x to 0..cropBoard.cellRef.y
        Corner.TopRight -> cropBoard.cellRef.x..<boardSize.size to 0..cropBoard.cellRef.y
        Corner.BottomLeft -> 0..cropBoard.cellRef.x to cropBoard.cellRef.y..<boardSize.size
        Corner.BottomRight -> cropBoard.cellRef.x..<boardSize.size to cropBoard.cellRef.y..<boardSize.size
        null -> 0..<boardSize.size to 0..<boardSize.size
    }
}

private fun getAspectRatio(
    boardSize: BoardSize,
    cropBoard: CropBoard?,
): Float {
    if (cropBoard == null) return 1f
    val spaceBorder = getBorderSpacing(boardSize, cropBoard)
    val (boardWidth, boardHeight) = getBoardSize(boardSize, cropBoard)
    return (boardWidth - 1 + spaceBorder) / (boardHeight - 1 + spaceBorder)
}

private fun getBoardSize(
    boardSize: BoardSize,
    cropBoard: CropBoard?,
): Pair<Int, Int> =

    when (cropBoard?.corner) {
        Corner.TopLeft -> cropBoard.cellRef.x to cropBoard.cellRef.y
        Corner.TopRight -> (boardSize.size - cropBoard.cellRef.x) to cropBoard.cellRef.y
        Corner.BottomLeft -> cropBoard.cellRef.x to (boardSize.size - cropBoard.cellRef.y)
        Corner.BottomRight -> (boardSize.size - cropBoard.cellRef.x) to (boardSize.size - cropBoard.cellRef.y)
        null -> boardSize.size to boardSize.size
    }

private fun getBorderSpacing(boardSize: BoardSize, cropBoard: CropBoard?): Float {
    val (x, y) = getBoardSize(boardSize, cropBoard)
    val isFullBoard = x == y && x == boardSize.size

    return if (isFullBoard) 2 * BORDER_SPACING_COEF else 0.5f + BORDER_SPACING_COEF
}

/** Size ratio of spacing border compare to cell spacing **/
private const val BORDER_SPACING_COEF: Float = 1f

private const val LINE_STROKE_COEF: Float = 19 * 2f
