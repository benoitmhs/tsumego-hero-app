package com.mrsanglier.tsumegohero.domain.choosepassword

import androidx.compose.runtime.Immutable
import com.mrsanglier.tsumegohero.domain.choosepassword.components.CriteriaCellState
import com.mrsanglier.tsumegohero.coreui.componants.button.THButtonState

@Immutable
data class ChoosePasswordViewModelState(
    val button: THButtonState,
    val criteriaCells: List<CriteriaCellState>,
)
