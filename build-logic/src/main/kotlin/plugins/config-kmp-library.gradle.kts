import config.ConfigurationKeys
import ext.configureCompileOptions
import ext.configureDefaultConfig
import ext.configurePlatformTargets
import ext.configureTestOptions

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
    id("setup-detekt")
}

android {
    compileSdk = ConfigurationKeys.sdkConfiguration.compileSdk

    configureDefaultConfig()
    configureCompileOptions()
    configureTestOptions()

    namespace = ConfigurationKeys.APPLICATION_ID.plus(name.replace(Regex("[^A-Za-z0-9]"), ""))
}

kotlin {
    configurePlatformTargets()
}
