package com.mrsanglier.tsumegohero.dashboard.di

import com.mrsanglier.tsumegohero.dashboard.navigation.DashboardViewModel
import com.mrsanglier.tsumegohero.dashboard.screens.play.PlayViewModel
import com.mrsanglier.tsumegohero.dashboard.screens.profile.ProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val dashboardModule = module {
    viewModelOf(::DashboardViewModel)
    viewModelOf(::PlayViewModel)
    viewModelOf(::ProfileViewModel)
}
