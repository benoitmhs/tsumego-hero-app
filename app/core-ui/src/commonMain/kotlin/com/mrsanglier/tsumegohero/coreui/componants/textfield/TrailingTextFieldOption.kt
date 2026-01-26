package com.mrsanglier.tsumegohero.coreui.componants.textfield

import com.mrsanglier.tsumegohero.coreui.componants.icon.IconSpec

sealed interface TrailingTextFieldOption {

    data class ActionIcon(
        val icon: IconSpec,
        val onClick: () -> Unit,
    ) : TrailingTextFieldOption
}
