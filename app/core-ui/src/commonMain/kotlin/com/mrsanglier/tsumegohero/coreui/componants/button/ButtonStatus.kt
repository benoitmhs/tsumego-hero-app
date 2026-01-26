package com.mrsanglier.tsumegohero.coreui.componants.button

sealed interface ButtonStatus {
    data object Enabled : ButtonStatus
    data object Disabled : ButtonStatus
    data object Loading : ButtonStatus
    data class Done(val onComplete: (() -> Unit)? = null) : ButtonStatus
}
