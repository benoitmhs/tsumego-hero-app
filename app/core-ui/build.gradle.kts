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

            // Animation
            implementation(libs.compottie)
            implementation(libs.compottie.dot)

            // Constraint layout
            implementation(libs.constraintlayout)

            // Datetime
            implementation(libs.datetime)

            // Haze (Blur effect)
            implementation(libs.haze)

            // Navigation
            implementation(libs.navigation.compose)

            // Serialization
            implementation(libs.kotlinx.serialization.json)

            // Project
            implementation(projects.kmp.core)
            implementation(projects.kmp.data)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.mrsanglier.tsumegohero.app.coreui.resources"
    generateResClass = auto
}
