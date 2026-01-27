package com.mrsanglier.tsumegohero.game.game.composable

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.mrsanglier.tsumegohero.data.model.BoardSize
import com.mrsanglier.tsumegohero.game.game.uimodel.BoardStyle

internal fun DrawScope.drawBoardGrid2(
    boardSize: BoardSize,
    cropBoard: CropBoard?,
    style: BoardStyle,
) {
    val croppedBoardSize = getBoardCroppedSize(boardSize, cropBoard)

    val cellSpacing = size.width / (croppedBoardSize - 1 + getBorderSpacing(boardSize, cropBoard))
    val boarderSpacing = cellSpacing * BORDER_SPACING_COEF
    val lineStroke = LINE_STROKE_COEF / croppedBoardSize

    // Draw column lines
    val startOffset = Offset(x = boarderSpacing, y = boarderSpacing)
    val endOffset = if (croppedBoardSize != boardSize.size) 0f else boarderSpacing

    for (x in 0..<croppedBoardSize) {
        val startOffset = startOffset + Offset(x = x * cellSpacing, y = 0f)
        val endOffset = startOffset.copy(y = this.size.height - endOffset)
        drawLine(
            color = style.gridColor,
            start = startOffset,
            end = endOffset,
            strokeWidth = lineStroke,
        )
    }

    // Draw row lines
    for (y in 0..<croppedBoardSize) {
        val startOffset = startOffset + Offset(x = 0f, y = y * cellSpacing)
        val endOffset = startOffset.copy(x = this.size.width - endOffset)
        drawLine(
            color = style.gridColor,
            start = startOffset,
            end = endOffset,
            strokeWidth = lineStroke,
        )
    }

    // Draw circle markers
    for (x in 0..<croppedBoardSize) {
        for (y in 0..<croppedBoardSize) {
            if ((x - 3) % 6 == 0 && (y - 3) % 6 == 0) {
                drawCircle(
                    center = Offset(x = x * cellSpacing, y = y * cellSpacing) + startOffset,
                    radius = cellSpacing * 0.1f,
                    color = style.gridColor,
                )
            }
        }
    }
}

private fun getBoardCroppedSize(
    boardSize: BoardSize,
    cropBoard: CropBoard?,
): Int {
    val (x, y) = when (cropBoard?.corner) {
        Corner.TopLeft -> cropBoard.cellRef.x to cropBoard.cellRef.y
        Corner.TopRight -> (boardSize.size - cropBoard.cellRef.x) to cropBoard.cellRef.y
        Corner.BottomLeft -> cropBoard.cellRef.x to (boardSize.size - cropBoard.cellRef.y)
        Corner.BottomRight -> (boardSize.size - cropBoard.cellRef.x) to (boardSize.size - cropBoard.cellRef.y)
        null -> boardSize.size to boardSize.size
    }
    return maxOf(x, y)
}

private fun getBorderSpacing(boardSize: BoardSize, cropBoard: CropBoard?): Float {
    val cropBoardSize = getBoardCroppedSize(boardSize, cropBoard)
    val isFullBoard = boardSize.size == cropBoardSize

    return if (isFullBoard) 2 * BORDER_SPACING_COEF else 0.5f + BORDER_SPACING_COEF
}

/** Size ratio of spacing border compare to cell spacing **/
private const val BORDER_SPACING_COEF: Float = 1f

private const val LINE_STROKE_COEF: Float = 19 * 2f