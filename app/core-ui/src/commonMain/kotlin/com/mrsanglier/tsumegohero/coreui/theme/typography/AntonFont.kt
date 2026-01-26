package com.mrsanglier.tsumegohero.coreui.theme.typography

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.mrsanglier.tsumegohero.app.coreui.resources.Res
import com.mrsanglier.tsumegohero.app.coreui.resources.anton_regular
import org.jetbrains.compose.resources.Font

internal val AntonFontFamily: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.anton_regular, weight = FontWeight.Normal)
    )