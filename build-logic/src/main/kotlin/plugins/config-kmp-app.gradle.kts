import config.ConfigurationKeys
import ext.configureBuildTypes
import ext.configureCompileOptions
import ext.configureDefaultConfig
import ext.configurePlatformTargets
import ext.configureTestOptions
import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.multiplatform")
    id("setup-detekt")
}

// Download strings script
val properties: Properties = Properties()
project.rootProject.file("local.properties").inputStream().use { properties.load(it) }

android {
    compileSdk = ConfigurationKeys.sdkConfiguration.compileSdk
    namespace = ConfigurationKeys.APPLICATION_ID

    defaultConfig {
        applicationId = ConfigurationKeys.APPLICATION_ID
        project
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    configureDefaultConfig()
    configureCompileOptions()
    configureTestOptions()
    configureBuildTypes()
}

kotlin {
    configurePlatformTargets()
}
