package com.mrsanglier.tsumegohero.coreui.componants.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.mrsanglier.tsumegohero.coreui.theme.THTheme

@Composable
fun THText(
    text: TextSpec?,
    style: TextStyle,
    color: Color = THTheme.colors.content,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    modifier: Modifier = Modifier,
) {
    text?.let {
        Text(
            text = text.annotated,
            style = style,
            color = color,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            modifier = modifier,
        )
    }
}
