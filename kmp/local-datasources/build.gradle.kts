plugins {
    id("config-kmp-library")
    id("setup-koin")
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // Datastore
            implementation(libs.datastore)
            implementation(libs.datastore.preferences)

            // Koin
            implementation(libs.koin.core)
            implementation(libs.koin.compose)

            // Room
            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)

            // Project modules
            implementation(projects.kmp.core)
            implementation(projects.kmp.data)

            // Serialization
            implementation(libs.kotlinx.serialization.json)
        }
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    listOf(
        "kspAndroid",
        "kspIosSimulatorArm64",
        "kspIosX64",
        "kspIosArm64"
    ).forEach {
        add(it, libs.room.compiler)
    }
}
