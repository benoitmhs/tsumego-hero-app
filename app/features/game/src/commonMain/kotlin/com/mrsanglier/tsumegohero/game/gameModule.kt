package com.mrsanglier.tsumegohero.game

import com.mrsanglier.tsumegohero.game.game.GameViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val gameModule = module {
    viewModelOf(::GameViewModel)
}
