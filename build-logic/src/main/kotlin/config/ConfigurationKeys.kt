package config

import model.JavaConfiguration
import model.SdkConfiguration
import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

internal object ConfigurationKeys {

    const val APPLICATION_ID: String = "com.mrsanglier.tsumegohero"
    const val APP_NAME = "TsumegoHeroApp"
    const val HAS_UNIT_TESTS_DEFAULT_VALUES = true

    val javaConfiguration = JavaConfiguration(
        javaVmTarget = JvmTarget.JVM_17,
        version = JavaVersion.VERSION_17
    )

    val sdkConfiguration = SdkConfiguration(
        minSdk = 24,
        targetSdk = 36,
        compileSdk = 36,
    )
}
