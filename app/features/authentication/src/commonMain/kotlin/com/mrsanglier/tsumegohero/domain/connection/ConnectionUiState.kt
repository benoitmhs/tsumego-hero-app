package com.mrsanglier.tsumegohero.domain.connection

import androidx.compose.runtime.Stable
import com.mrsanglier.tsumegohero.coreui.componants.button.THButtonState

@Stable
data class ConnectionUiState(
    val state: State,
    val loginButtonState: THButtonState,
    val registerButtonState: THButtonState,
) {
    enum class State {
        Idle, Login, Register;
    }
}