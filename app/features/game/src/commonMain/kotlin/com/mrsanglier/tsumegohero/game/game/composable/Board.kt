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
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.toSize
import com.mrsanglier.tsumegohero.coreui.theme.THTheme
import com.mrsanglier.tsumegohero.game.game.uimodel.BoardStyle
import com.mrsanglier.tsumegohero.game.model.BoardSize
import com.mrsanglier.tsumegohero.game.model.Cell
import com.mrsanglier.tsumegohero.game.model.CropBoard
import com.mrsanglier.tsumegohero.game.utils.convertOffsetToCell
import com.mrsanglier.tsumegohero.game.utils.getScaleFactor
import com.mrsanglier.tsumegohero.game.utils.getScalingPivot
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun Board(
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
                .pointerInput(scaleFactor, cropBoard) {
                    detectTapGestures { offset ->
                        convertOffsetToCell(
                            offset = offset,
                            size = size.toSize(),
                            scaleFactor = scaleFactor,
                            boardSize = boardSize,
                            cropBoard = cropBoard,
                        )?.let(onClickCell::invoke)
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


