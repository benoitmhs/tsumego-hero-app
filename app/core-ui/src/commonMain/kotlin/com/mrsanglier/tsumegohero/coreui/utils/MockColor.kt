package com.mrsanglier.tsumegohero.coreui.utils

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

object MockColor {
    fun pastelColor(seed: String): Color {
        val random = Random(seed = seed.hashCode())
        val r = random.nextInt(110, 256)  // Light red
        val g = random.nextInt(110, 256)  // Light green
        val b = random.nextInt(110, 256)  // Light blue

        return Color(r, g, b)
    }

    fun pastelColor2(seed: String): Color {
        val random = Random(seed = seed.hashCode())

        val hue = random.nextFloat() * 360
        val saturation = random.nextFloat() * 0.5f + 0.5f
        val lightness = random.nextFloat() * 0.3f + 0.6f
        val color = Color.hsv(hue = hue, saturation, lightness)

        return color
    }

    private fun generateHue(random: Random): Float {
        val hueRange = listOf(
            0f to 30f,  // Exclude red-brown hues (0°-30°)
            60f to 180f,  // Green to blue hues
            210f to 360f  // Violet to red hues
        )
        val (minHue, maxHue) = hueRange[random.nextInt(hueRange.size)]

        return random.nextFloat() * (maxHue - minHue) + minHue
    }
}
