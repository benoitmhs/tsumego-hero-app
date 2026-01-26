package com.mrsanglier.tsumegohero.coreui.componants.icon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Stable
sealed interface IconSpec {
    val contentDescription: TextSpec?
    val tint: (@Composable () -> Color)?

    @Composable
    fun painter(): Painter

    data class Resources(
        private val drawableRes: DrawableResource,
        override val contentDescription: TextSpec? = null,
        override val tint: (@Composable () -> Color)? = null,
    ) : IconSpec {

        @Composable
        override fun painter(): Painter = painterResource(resource = drawableRes)
    }

    data class Vector(
        private val imageVector: ImageVector,
        override val contentDescription: TextSpec? = null,
        override val tint: (@Composable () -> Color)? = null,
    ) : IconSpec {

        @Composable
        override fun painter(): Painter = rememberVectorPainter(imageVector)
    }
}
