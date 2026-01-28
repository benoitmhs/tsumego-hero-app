package com.mrsanglier.tsumegohero.game.game.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.toSize
import com.mrsanglier.tsumegohero.coreui.theme.THTheme
import com.mrsanglier.tsumegohero.game.game.uimodel.BoardStyle
import com.mrsanglier.tsumegohero.game.model.BoardSize
import com.mrsanglier.tsumegohero.game.model.Cell
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun Board(
    boardSize: BoardSize = BoardSize.X19,
    modifier: Modifier = Modifier,
    style: BoardStyle = BoardStyle.Default,
    cropBoard: CropBoard? = null,
    blackStones: Set<Cell> = emptySet(),
    whiteStones: Set<Cell> = emptySet(),
    onClickCell: (Cell) -> Unit = {},
) {
    val blackStoneImageBitmap = imageResource(style.blackStoneRes)
    val whiteStoneImageBitmap = imageResource(style.whiteStoneRes)
    val scaleFactor = remember(boardSize, cropBoard) {
        cropBoard.getScaleFactor(boardSize)
    }

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
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        // Reverse scaling to get click position in full board
                        val scaledOffset = unscaleOffset(
                            offset = offset,
                            scale = scaleFactor,
                            pivot = cropBoard?.corner.getScalingPivot(size.toSize()),
                        )

                        val cellSize = size.width / (BORDER_SPACING_COEF + boardSize.size)

                        // Remove border offset
                        val borderDiff = ((cellSize * BORDER_SPACING_COEF) - (cellSize / 2)).coerceAtLeast(0f)
                        val rawOffset = scaledOffset - Offset(borderDiff, borderDiff)

                        // Convert into Cell
                        val (x, y) = rawOffset.div(cellSize).let { (xf, yf) -> xf.toInt() to yf.toInt() }
                        if (x in 0..<boardSize.size && y in 0..<boardSize.size) {
                            onClickCell(Cell(x, y))
                        }
                    }
                },
        ) {
            scale(
                scaleX = scaleFactor,
                scaleY = scaleFactor,
                pivot = cropBoard?.corner.getScalingPivot(size),
            ) {
                drawBoard(
                    boardSize = boardSize,
                    style = style,
                    blackStones = blackStones,
                    whiteStones = whiteStones,
                    blackStoneImageBitmap = blackStoneImageBitmap,
                    whiteStoneImageBitmap = whiteStoneImageBitmap,
                )
            }
        }
    }
}

private fun CropBoard?.getScaleFactor(boardSize: BoardSize): Float {
    if (this == null) return 1f
    val originSize = boardSize.size

    val (scaledWidth, scaledHeight) = when (this.corner) {
        Corner.TopLeft -> cellRef.x to cellRef.y
        Corner.TopRight -> originSize - cellRef.x to cellRef.y
        Corner.BottomLeft -> cellRef.x to originSize - cellRef.y
        Corner.BottomRight -> originSize - cellRef.x to originSize - cellRef.y
    }
    val scaledSize = maxOf(scaledWidth, scaledHeight)

    if (scaledSize == originSize) return 1f

    return (originSize - 1 + (2 * BORDER_SPACING_COEF)) / (scaledSize - 1 + BORDER_SPACING_COEF + 0.5f)
}

private fun Corner?.getScalingPivot(size: Size): Offset {
    return when (this) {
        Corner.TopLeft, null -> Offset.Zero
        Corner.TopRight -> Offset(size.width, 0f)
        Corner.BottomLeft -> Offset(0f, size.height)
        Corner.BottomRight -> Offset(size.width, size.height)
    }
}

private fun unscaleOffset(
    offset: Offset,
    scale: Float,
    pivot: Offset
): Offset {
    return (offset - pivot) / scale + pivot
}
