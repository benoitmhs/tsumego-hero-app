package com.mrsanglier.tsumegohero.remotedatasources.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual fun getPlatformEngine(): HttpClientEngine = OkHttp.create()
