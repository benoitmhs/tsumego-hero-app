package com.mrsanglier.tsumegohero.coreui.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.node.Ref

@Composable
inline fun <T> AnimatedNullableVisibility(
    value: T?,
    modifier: Modifier = Modifier,
    visible: (T) -> Boolean = { true },
    enter: EnterTransition = fadeIn(),
    exit: ExitTransition = fadeOut(),
    label: String = "AnimatedVisibility",
    crossinline content: @Composable AnimatedVisibilityScope.(T) -> Unit,
) {
    val ref = remember { Ref<T>() }
    value?.let { ref.value = it }

    AnimatedVisibility(
        visible = value?.let(visible) ?: false,
        modifier = modifier,
        enter = enter,
        exit = exit,
        label = label,
        content = { ref.value?.let { content(it) } },
    )
}
