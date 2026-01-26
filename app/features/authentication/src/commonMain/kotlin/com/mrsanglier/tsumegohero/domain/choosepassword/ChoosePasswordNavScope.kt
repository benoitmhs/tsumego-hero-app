package com.mrsanglier.tsumegohero.domain.choosepassword

import com.mrsanglier.tsumegohero.domain.choosepseudo.ChoosePseudoDestination

data class ChoosePasswordNavScope(
    val navigateBack: () -> Unit,
    val navigateToChoosePseudo: (ChoosePseudoDestination) -> Unit,
)
