package com.mrsanglier.tsumegohero.coreui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mrsanglier.tsumegohero.coreui.utils.KeyboardState
import com.mrsanglier.tsumegohero.coreui.utils.rememberKeyboardState
import kotlinx.coroutines.flow.StateFlow

/**
 * Logic to safe navigation
 * Close keyboard before navigate to avoid glitch
 */
@Composable
fun <T> safeNavigation(
    navigationState: StateFlow<T?>,
    consumeNavigation: () -> Unit,
    block: (T) -> Unit,
) {
    val navEvent by navigationState.collectAsStateWithLifecycle()
    val keyboardState by rememberKeyboardState()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(navEvent, keyboardState) {
        navEvent?.let { navValue ->
            // Navigate only when keyboard is hidden to avoid glitch
            when (keyboardState) {
                KeyboardState.Shown, KeyboardState.Appear -> focusManager.clearFocus()
                KeyboardState.Disappear -> {} // no-op, wait keyboard closing
                KeyboardState.Hidden -> {
                    block(navValue)
                    consumeNavigation()
                }
            }
        }
    }
}
