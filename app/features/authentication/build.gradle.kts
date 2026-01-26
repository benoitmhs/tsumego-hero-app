plugins {
    id("config-kmp-library")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    sourceSets {

        androidMain.dependencies {
            implementation(compose.preview)
        }
        commonMain.dependencies {
            // Compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            // Koin
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)

            // Haze
            implementation(libs.haze)

            // Log
            implementation(libs.kermit)

            // Navigation
            implementation(libs.navigation.compose)
            implementation(libs.androidx.lifecycle.viewmodel)

            // Serialization
            implementation(libs.kotlinx.serialization.json)

            // Project
            implementation(projects.app.coreUi)
            implementation(projects.kmp.core)
            implementation(projects.kmp.data)
            implementation(projects.kmp.domain.authentication)
        }
    }
}
