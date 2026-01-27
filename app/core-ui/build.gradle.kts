plugins {
    id("config-kmp-library")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    sourceSets {

        androidMain.dependencies {
            implementation(libs.compose.preview)
        }
        commonMain.dependencies {
            // Compose
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.preview)

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
