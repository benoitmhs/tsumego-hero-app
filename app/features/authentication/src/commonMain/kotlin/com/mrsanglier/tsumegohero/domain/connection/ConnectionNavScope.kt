package com.mrsanglier.tsumegohero.domain.connection

import com.mrsanglier.tsumegohero.domain.choosepassword.ChoosePasswordDestination

data class ConnectionNavScope(
    val navigateBack: () -> Unit,
    val navigateToChoosePassword: (choosePasswordDestination: ChoosePasswordDestination) -> Unit,
)
