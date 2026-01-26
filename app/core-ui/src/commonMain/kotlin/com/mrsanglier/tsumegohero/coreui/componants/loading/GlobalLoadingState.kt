package com.mrsanglier.tsumegohero.coreui.componants.loading

enum class GlobalLoadingState(val isBlocking: Boolean, val isLoading: Boolean) {
    None(false, false), Blocking(true, false), Loading(true, true)
}