package io.github.anjalymsajeev.youkoso.buildlogic

import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

/** Compiler settings shared by every Kotlin module, including the sample app. */
internal fun Project.configureKotlinCompiler() {
    val target = JvmTarget.fromTarget(libs.versionOf("jvmTarget"))
    tasks.withType<KotlinJvmCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(target)
            allWarningsAsErrors.set(true)
        }
    }
}

/**
 * Published library modules use explicit API mode: every public declaration needs a visibility
 * modifier and an explicit return type, so nothing becomes public API by accident.
 */
internal fun Project.configurePublishedKotlinLibrary() {
    configureKotlinCompiler()
    extensions.getByType<KotlinBaseExtension>().explicitApi()
}
