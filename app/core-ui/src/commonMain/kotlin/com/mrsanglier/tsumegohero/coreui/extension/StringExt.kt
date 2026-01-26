package com.mrsanglier.tsumegohero.coreui.extension

import androidx.compose.ui.text.AnnotatedString
import com.mrsanglier.tsumegohero.coreui.componants.text.TextSpec

fun String.toTextSpec(): TextSpec = TextSpec.Raw(this)
fun AnnotatedString.toTextSpec(): TextSpec = TextSpec.Annotated(this)
