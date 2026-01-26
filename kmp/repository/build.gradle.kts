plugins {
    id("config-kmp-library")
    id("setup-koin")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            // Project
            implementation(projects.kmp.core)
            implementation(projects.kmp.data)
            implementation(projects.kmp.localDatasources)
            implementation(projects.kmp.remoteDatasources)
        }
    }
}
