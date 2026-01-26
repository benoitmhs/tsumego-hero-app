package com.mrsanglier.tsumegohero.remotedatasources.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val remoteModule = module {
}

expect fun getPlatformEngine(): HttpClientEngine

private fun provideKtorHttpClient(): HttpClient = HttpClient(getPlatformEngine()) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        })
    }
    expectSuccess = true
}
