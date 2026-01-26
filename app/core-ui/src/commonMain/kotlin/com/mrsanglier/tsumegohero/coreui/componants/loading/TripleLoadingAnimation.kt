package com.mrsanglier.tsumegohero.coreui.componants.loading

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

private const val PaddingPercentageOuterCircle = 0.15f
private const val PaddingPercentageInnerCircle = 0.3f
private const val PositionStartOffsetOuterCircle = 90f
private const val PositionStartOffsetInnerCircle = 135f

@Composable
internal fun TripleOrbitLoadingAnimation(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
        ),
        label = "rotation animation"
    )
    var width by remember {
        mutableIntStateOf(0)
    }
    Box(
        modifier = modifier
            .size(LoadingSize)
            .onSizeChanged {
                width = it.width
            },
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            strokeWidth = ProgressStroke,
            color = THTheme.colors.contentTint,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    rotationZ = rotation
                }
        )
        CircularProgressIndicator(
            strokeWidth = ProgressStroke,
            color = THTheme.colors.contentTint,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    with(LocalDensity.current) {
                        (width * PaddingPercentageInnerCircle).toDp()
                    }
                )
                .graphicsLayer {
                    rotationZ = rotation + PositionStartOffsetInnerCircle
                }
        )
        CircularProgressIndicator(
            strokeWidth = ProgressStroke,
            color = THTheme.colors.contentTint,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    with(LocalDensity.current) {
                        (width * PaddingPercentageOuterCircle).toDp()
                    }
                )
                .graphicsLayer {
                    rotationZ = rotation + PositionStartOffsetOuterCircle
                }
        )
    }
}

private val LoadingSize: Dp = 120.dp
private val ProgressStroke: Dp = 3.dp
