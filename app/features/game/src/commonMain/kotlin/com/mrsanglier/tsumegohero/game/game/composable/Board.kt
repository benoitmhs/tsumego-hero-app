package com.mrsanglier.tsumegohero.game.game.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import com.mrsanglier.tsumegohero.coreui.theme.THTheme
import com.mrsanglier.tsumegohero.data.model.BoardSize
import com.mrsanglier.tsumegohero.data.model.Cell
import com.mrsanglier.tsumegohero.game.game.uimodel.BoardStyle
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
) {
    val blackStoneImageBitmap = imageResource(style.blackStoneRes)
    val whiteStoneImageBitmap = imageResource(style.whiteStoneRes)

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
            drawBoardGrid(
                boardSize = boardSize,
                cropBoard = cropBoard,
                style = style,
                blackStones = blackStones,
                whiteStones = whiteStones,
                blackStoneImageBitmap = blackStoneImageBitmap,
                whiteStoneImageBitmap = whiteStoneImageBitmap,
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
