package com.mrsanglier.tsumegohero.coreui.theme.typography

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.mrsanglier.tsumegohero.app.coreui.resources.Res
import com.mrsanglier.tsumegohero.app.coreui.resources.lexend_black
import com.mrsanglier.tsumegohero.app.coreui.resources.lexend_extralight
import com.mrsanglier.tsumegohero.app.coreui.resources.lexend_light
import com.mrsanglier.tsumegohero.app.coreui.resources.lexend_medium
import com.mrsanglier.tsumegohero.app.coreui.resources.lexend_regular
import com.mrsanglier.tsumegohero.app.coreui.resources.lexend_semibold
import com.mrsanglier.tsumegohero.app.coreui.resources.lexend_thin
import org.jetbrains.compose.resources.Font

internal val LexendFontFamily: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.lexend_thin, weight = FontWeight.Thin),
        Font(Res.font.lexend_extralight, weight = FontWeight.ExtraLight),
        Font(Res.font.lexend_light, weight = FontWeight.Light),
        Font(Res.font.lexend_regular, weight = FontWeight.Normal),
        Font(Res.font.lexend_medium, weight = FontWeight.Medium),
        Font(Res.font.lexend_semibold, weight = FontWeight.SemiBold),
        Font(Res.font.lexend_thin, weight = FontWeight.Bold),
        Font(Res.font.lexend_thin, weight = FontWeight.ExtraBold),
        Font(Res.font.lexend_black, weight = FontWeight.Black),
    )
