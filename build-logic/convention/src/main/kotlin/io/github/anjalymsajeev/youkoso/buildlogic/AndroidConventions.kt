package io.github.anjalymsajeev.youkoso.buildlogic

import com.android.build.api.dsl.CompileOptions
import com.android.build.api.dsl.Lint
import org.gradle.api.JavaVersion
import org.gradle.api.Project

internal fun Project.javaVersion(): JavaVersion = JavaVersion.toVersion(libs.versionOf("jvmTarget"))

internal fun CompileOptions.configureJava(project: Project) {
    sourceCompatibility = project.javaVersion()
    targetCompatibility = project.javaVersion()
}

/**
 * Lint is a release gate: any warning fails the build. Checks that report newer dependency
 * versions are disabled because they depend on network state; Dependabot handles updates.
 */
internal fun Lint.configureLint() {
    abortOnError = true
    warningsAsErrors = true
    checkDependencies = false
    disable += setOf(
        "GradleDependency",
        "NewerVersionAvailable",
        "AndroidGradlePluginVersion",
        "OldTargetApi"
    )
}
