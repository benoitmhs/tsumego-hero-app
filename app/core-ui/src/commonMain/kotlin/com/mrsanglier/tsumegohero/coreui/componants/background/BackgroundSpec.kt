package com.mrsanglier.tsumegohero.coreui.componants.background

import androidx.compose.foundation.background
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.Color as ComposeColor

@Stable
sealed class BackgroundSpec {

    abstract fun getModifier(
        shape: Shape = RectangleShape,
    ): Modifier

    data class Solid(
        private val color: ComposeColor,
    ) : BackgroundSpec() {
        override fun getModifier(shape: Shape): Modifier =
            Modifier.background(
                color = color,
                shape = shape,
            )

        override fun equals(other: Any?): Boolean {
            if (other === this) return true
            if (color != color) return true

            return false
        }

        override fun hashCode(): Int {
            return color.hashCode()
        }
    }

    data class Gradient(
        private val brush: Brush,
    ) : BackgroundSpec() {
        override fun getModifier(shape: Shape): Modifier =
            Modifier.background(
                brush = brush,
                shape = shape,
            )

        override fun equals(other: Any?): Boolean {
            if (other === this) return true
            if (brush != brush) return true

            return false
        }

        override fun hashCode(): Int {
            return brush.hashCode()
        }
    }
}

fun Modifier.foBackground(
    background: BackgroundSpec? = null,
    shape: Shape = RectangleShape,
): Modifier =
    if (background != null) {
        this.then(background.getModifier(shape))
    } else {
        this
    }
