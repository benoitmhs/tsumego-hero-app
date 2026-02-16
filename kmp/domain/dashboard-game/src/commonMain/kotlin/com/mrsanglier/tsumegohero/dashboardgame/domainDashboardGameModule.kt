package com.mrsanglier.tsumegohero.dashboardgame

import com.mrsanglier.tsumegohero.dashboardgame.usecase.GetTsumegoItemUseCase
import com.mrsanglier.tsumegohero.dashboardgame.usecase.PrepopulateTsumegoDBUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainDashboardGameModule: Module = module {
    singleOf(::GetTsumegoItemUseCase)
    singleOf(::PrepopulateTsumegoDBUseCase)
}
