plugins {
    id("config-kmp-library")
    id("setup-koin")
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            // Ktor
            implementation(libs.ktor.client.okhttp)
        }
        commonMain.dependencies {
            // Koin
            implementation(libs.koin.core)

            // Ktor
            implementation(libs.ktor.core)
            implementation(libs.ktor.negotiation)
            implementation(libs.ktor.json)

            // Serialization
            implementation(libs.kotlinx.serialization.json)

            // Project modules
            implementation(projects.kmp.core)
            implementation(projects.kmp.data)
        }
        iosMain.dependencies {
            // Ktor
            implementation(libs.ktor.client.darwin)
        }
    }
}
