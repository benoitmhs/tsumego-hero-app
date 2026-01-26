package com.mrsanglier.tsumegohero.coreui.utils

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity
import com.mrsanglier.tsumegohero.coreui.extension.updateDistinct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun rememberKeyboardState(): State<KeyboardState> {
    val coroutineScope = rememberCoroutineScope()
    var job: Job? = null

    val keyboardState: MutableState<KeyboardState> = remember { mutableStateOf(KeyboardState.Hidden) }

    var previousImeBottom by remember {
        mutableIntStateOf(0)
    }
    val imeBottom = WindowInsets.ime.getBottom(LocalDensity.current)

    var maxImeHeight by remember { mutableIntStateOf(1) }
    maxImeHeight = max(imeBottom, maxImeHeight)

    remember(imeBottom) {
        job?.cancel()
        val state = when {
            imeBottom == 0 -> KeyboardState.Hidden
            imeBottom < previousImeBottom -> KeyboardState.Disappear
            else -> {
                job = coroutineScope.launch(Dispatchers.Default) {
                    delay(30.milliseconds)
                    keyboardState.updateDistinct(KeyboardState.Shown)
                }
                KeyboardState.Appear
            }
        }
        previousImeBottom = imeBottom
        keyboardState.updateDistinct(state)
    }

    return keyboardState
}

enum class KeyboardState {
    Hidden, Appear, Shown, Disappear,
}
