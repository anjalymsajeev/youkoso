package io.github.anjalymsajeev.youkoso.buildlogic

import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType

internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun VersionCatalog.versionOf(alias: String): String = findVersion(alias).orElseThrow {
    IllegalStateException("Version '$alias' is missing from gradle/libs.versions.toml")
}.requiredVersion

internal fun VersionCatalog.intVersionOf(alias: String): Int = versionOf(alias).toInt()

internal fun VersionCatalog.library(alias: String): Provider<MinimalExternalModuleDependency> =
    findLibrary(alias).orElseThrow {
        IllegalStateException("Library '$alias' is missing from gradle/libs.versions.toml")
    }

internal const val GITHUB_REPOSITORY_URL = "https://github.com/anjalymsajeev/youkoso"
