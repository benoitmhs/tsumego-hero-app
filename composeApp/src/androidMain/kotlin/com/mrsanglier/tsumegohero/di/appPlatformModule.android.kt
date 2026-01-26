package com.mrsanglier.tsumegohero.di

import com.mrsanglier.tsumegohero.coreui.utils.AppIntentManager
import com.mrsanglier.tsumegohero.coreui.utils.AppIntentManagerImpl
import com.mrsanglier.tsumegohero.utils.getAndroidAppVersion
import com.mrsanglier.tsumegohero.data.model.AppVersion
import org.koin.core.module.Module
import org.koin.dsl.module

actual val appPlatformModule: Module = module {
    single<AppIntentManager> { AppIntentManagerImpl(get()) }
    single<AppVersion> { getAndroidAppVersion(get()) }
}
