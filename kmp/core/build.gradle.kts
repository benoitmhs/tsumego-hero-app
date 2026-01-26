plugins {
    id("config-kmp-library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.datetime)
            implementation(libs.kermit)
            implementation(libs.kotlinx.coroutines)

            // Serialization
            implementation(libs.kotlinx.serialization.json)
        }
    }
}
