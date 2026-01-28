package com.mrsanglier.tsumegohero.game.game.composable

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.round
import com.mrsanglier.tsumegohero.data.model.BoardSize
import com.mrsanglier.tsumegohero.data.model.Cell
import com.mrsanglier.tsumegohero.game.game.uimodel.BoardStyle

internal fun DrawScope.drawBoard(
    boardSize: BoardSize,
    style: BoardStyle,
    blackStones: Set<Cell>,
    whiteStones: Set<Cell>,
    blackStoneImageBitmap: ImageBitmap,
    whiteStoneImageBitmap: ImageBitmap,
) {
    val cellSpacing = minOf(size.width, size.height) / (boardSize.size - 1 + 2 * BORDER_SPACING_COEF)
    val boarderSpacing = cellSpacing * BORDER_SPACING_COEF

    val startOffset = Offset(x = boarderSpacing, y = boarderSpacing)
    val endOffset = boarderSpacing

    // Draw column lines
    for (x in 0..<boardSize.size) {
        val startOffset = startOffset + Offset(x = x * cellSpacing, y = 0f)
        val endOffset = startOffset.copy(y = this.size.height - endOffset)
        drawLine(
            color = style.gridColor,
            start = startOffset,
            end = endOffset,
            strokeWidth = LINE_STROKE,
        )
    }

    // Draw row lines
    for (y in 0..<boardSize.size) {
        val startOffset = startOffset + Offset(x = 0f, y = y * cellSpacing)
        val endOffset = startOffset.copy(x = this.size.width - endOffset)
        drawLine(
            color = style.gridColor,
            start = startOffset,
            end = endOffset,
            strokeWidth = LINE_STROKE,
        )
    }

    // Draw hoshi
    for (x in 0..<boardSize.size) {
        for (y in 0..<boardSize.size) {
            if ((x - 3) % 6 == 0 && (y - 3) % 6 == 0) {
                drawCircle(
                    center = Offset(x = x * cellSpacing, y = y * cellSpacing) + startOffset,
                    radius = cellSpacing * 0.1f,
                    color = style.gridColor,
                )
            }
        }
    }

    // Draw Stones
    val stoneSize = cellSpacing * STONE_SIZE_RATIO
    for (stone in blackStones) {
        drawImage(
            image = blackStoneImageBitmap,
            dstSize = IntSize(
                width = stoneSize.toInt(),
                height = stoneSize.toInt(),
            ),
            dstOffset = (Offset(
                x = (stone.x * cellSpacing) - stoneSize / 2,
                y = (stone.y * cellSpacing) - stoneSize / 2,
            ) + startOffset).round(),
        )
    }
    for (stone in whiteStones) {
        drawImage(
            image = whiteStoneImageBitmap,
            dstSize = IntSize(
                width = stoneSize.toInt(),
                height = stoneSize.toInt(),
            ),
            dstOffset = (Offset(
                x = (stone.x * cellSpacing) - stoneSize / 2,
                y = (stone.y * cellSpacing) - stoneSize / 2,
            ) + startOffset).round(),
        )
    }
}

/** Size ratio of spacing border compare to cell spacing **/
internal const val BORDER_SPACING_COEF: Float = 1f

private const val LINE_STROKE: Float = 2f
private const val STONE_SIZE_RATIO: Float = 0.95f