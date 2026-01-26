package com.mrsanglier.tsumegohero.coreui.extension

import androidx.compose.runtime.MutableState

fun <T> MutableState<T>.updateDistinct(value: T) {
    if (this.value != value) {
        this.value = value
    }
}
