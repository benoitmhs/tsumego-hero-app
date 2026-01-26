package com.mrsanglier.tsumegohero.coreui.componants.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
fun THTextResponsive(
    text: TextSpec?,
    style: TextStyle,
    modifier: Modifier = Modifier,
    color: Color = THTheme.colors.content,
    minFontSize: TextUnit = style.fontSize * DefaultMinFontSizeRatio,
    textAlign: TextAlign? = null,
    softWrap: Boolean = true,
    maxLines: Int = 1,
) {
    var textStyle by remember(style) { mutableStateOf(style) }
    var readyToDraw by remember { mutableStateOf(false) }

    text?.string?.let { _text ->
        Text(
            modifier = modifier
                .drawWithContent {
                    if (readyToDraw) drawContent()
                },
            text = _text,
            color = color,
            textAlign = textAlign,
            softWrap = softWrap,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            style = textStyle,
            onTextLayout = { textLayoutResult ->
                if (textLayoutResult.didOverflowHeight && textStyle.fontSize > minFontSize) {
                    textStyle = textStyle.copy(
                        fontSize = textStyle.fontSize * RatioFontSizeReductionResponsiveText,
                        lineHeight = if (textStyle.lineHeight != TextUnit.Unspecified) {
                            textStyle.lineHeight * RatioLineHeightReductionResponsiveText
                        } else {
                            textStyle.lineHeight
                        },
                    )
                } else {
                    readyToDraw = true
                }
            },
        )
    }
}

private const val DefaultMinFontSizeRatio: Float = 0.7f
private const val RatioFontSizeReductionResponsiveText: Float = 0.9f
private const val RatioLineHeightReductionResponsiveText: Float = 0.95f
