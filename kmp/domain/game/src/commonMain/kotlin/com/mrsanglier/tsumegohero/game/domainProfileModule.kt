package com.mrsanglier.tsumegohero.game

import com.mrsanglier.tsumegohero.game.usecase.LoadTsumegoUseCase
import com.mrsanglier.tsumegohero.game.usecase.PlayMoveUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainGameModule: Module = module {
    singleOf(::LoadTsumegoUseCase)
    singleOf(::PlayMoveUseCase)
}
