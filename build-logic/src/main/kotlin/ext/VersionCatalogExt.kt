package ext

import org.gradle.api.artifacts.VersionCatalog

// Koin
internal fun VersionCatalog.koinCoreDependency() = findLibrary("koin-core").get()
internal fun VersionCatalog.koinAnnotationsDependency() = findLibrary("koin-annotations").get()
internal fun VersionCatalog.koinCoroutines() = findLibrary("koin-coroutines").get()
internal fun VersionCatalog.koinCompiler() = findLibrary("koin-compiler").get()
