plugins {
    id("config-kmp-library")
    id("setup-koin")
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // Datetime
            api(libs.datetime)

            // Serialization
            implementation(libs.kotlinx.serialization.json)

            implementation(projects.kmp.core)
        }
    }
}