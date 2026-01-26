package com.mrsanglier.tsumegohero.di

import com.mrsanglier.tsumegohero.BuildKonfig
import com.mrsanglier.tsumegohero.data.model.AppConfig

fun getAppConfig(): AppConfig = AppConfig(
    environment = AppConfig.Environment.buildFromString(BuildKonfig.ENVIRONMENT),
)
