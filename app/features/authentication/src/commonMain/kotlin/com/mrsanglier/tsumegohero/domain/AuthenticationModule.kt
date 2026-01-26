package com.mrsanglier.tsumegohero.domain

import com.mrsanglier.tsumegohero.domain.choosepassword.ChoosePasswordViewModel
import com.mrsanglier.tsumegohero.domain.choosepseudo.ChoosePseudoViewModel
import com.mrsanglier.tsumegohero.domain.connection.ConnectionViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authenticationModule = module {
    viewModelOf(::ConnectionViewModel)
    viewModelOf(::ChoosePasswordViewModel)
    viewModelOf(::ChoosePseudoViewModel)
}
