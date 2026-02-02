package com.mrsanglier.tsumegohero.game

import com.mrsanglier.tsumegohero.game.delegate.GetCropBoardDelegateImpl
import com.mrsanglier.tsumegohero.game.delegate.ParseSgfTsumegoDelegateImpl
import com.mrsanglier.tsumegohero.game.delegate.PlayMoveDelegateImpl
import com.mrsanglier.tsumegohero.game.usecase.PlayFreeMoveUseCase
import com.mrsanglier.tsumegohero.game.usecase.PlayOpponentMoveUseCase
import com.mrsanglier.tsumegohero.game.usecase.PlayPlayerMoveUseCase
import com.mrsanglier.tsumegohero.game.usecase.PlayReviewMoveUseCase
import com.mrsanglier.tsumegohero.game.usecase.RestartGameUseCase
import com.mrsanglier.tsumegohero.game.usecase.StartGameUseCase
import com.mrsanglier.tsumegohero.game.usecase.StartReviewUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainGameModule: Module = module {
    // UseCase
    singleOf(::PlayFreeMoveUseCase)
    singleOf(::PlayPlayerMoveUseCase)
    singleOf(::PlayReviewMoveUseCase)
    singleOf(::PlayOpponentMoveUseCase)
    singleOf(::RestartGameUseCase)
    singleOf(::StartGameUseCase)
    singleOf(::StartReviewUseCase)

    // Delegate
    singleOf(::GetCropBoardDelegateImpl)
    singleOf(::ParseSgfTsumegoDelegateImpl)
    singleOf(::PlayMoveDelegateImpl)
}
