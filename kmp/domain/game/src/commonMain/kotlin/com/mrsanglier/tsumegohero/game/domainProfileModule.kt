package com.mrsanglier.tsumegohero.game

import com.mrsanglier.tsumegohero.game.delegate.GetCropBoardDelegateImpl
import com.mrsanglier.tsumegohero.game.delegate.ParseSgfTsumegoDelegateImpl
import com.mrsanglier.tsumegohero.game.delegate.PlayMoveDelegateImpl
import com.mrsanglier.tsumegohero.game.usecase.PlayOpponentMoveUseCase
import com.mrsanglier.tsumegohero.game.usecase.PlayPlayerMoveUseCase
import com.mrsanglier.tsumegohero.game.usecase.RestartGameUseCase
import com.mrsanglier.tsumegohero.game.usecase.StartGameUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainGameModule: Module = module {
    // UseCase
    singleOf(::PlayPlayerMoveUseCase)
    singleOf(::PlayOpponentMoveUseCase)
    singleOf(::RestartGameUseCase)
    singleOf(::StartGameUseCase)

    // Delegate
    singleOf(::GetCropBoardDelegateImpl)
    singleOf(::ParseSgfTsumegoDelegateImpl)
    singleOf(::PlayMoveDelegateImpl)
}
