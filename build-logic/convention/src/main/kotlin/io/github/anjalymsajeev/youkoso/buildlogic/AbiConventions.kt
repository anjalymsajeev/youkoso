package io.github.anjalymsajeev.youkoso.buildlogic

import kotlinx.validation.KotlinApiBuildTask
import kotlinx.validation.KotlinApiCompareTask
import org.gradle.api.Project
import org.gradle.api.tasks.Sync
import org.gradle.kotlin.dsl.register
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

/**
 * Binary-compatibility validation (BCV). Every published module records its public API in
 * `api/<module>.api`. `./gradlew apiCheck` (part of `check`) fails when the compiled public API
 * differs from that file; `./gradlew apiDump` accepts an intentional change.
 *
 * Why the Android modules are wired by hand is explained in docs/adr/0004-abi-validation.md.
 */
internal fun Project.configureJvmAbiValidation() {
    pluginManager.apply("org.jetbrains.kotlinx.binary-compatibility-validator")
}

/**
 * Registers BCV's own task types against the release Kotlin classes of an Android library.
 * BCV only configures Android modules that apply `org.jetbrains.kotlin.android`, which AGP 9's
 * built-in Kotlin support no longer uses, so the same wiring is done here explicitly.
 */
internal fun Project.configureAndroidAbiValidation() {
    val apiFileName = "${project.name}.api"
    val apiBuild = tasks.register<KotlinApiBuildTask>("apiBuild") {
        description = "Builds the public API dump from the release Kotlin classes."
        inputClassesDirs.from(
            tasks.named("compileReleaseKotlin").map {
                (it as KotlinJvmCompile).destinationDirectory
            }
        )
        outputApiFile.set(layout.buildDirectory.file("api/$apiFileName"))
    }
    val apiCheck = tasks.register<KotlinApiCompareTask>("apiCheck") {
        group = "verification"
        description = "Fails if the public API differs from api/$apiFileName."
        projectApiFile.set(layout.projectDirectory.file("api/$apiFileName"))
        generatedApiFile.set(apiBuild.flatMap { it.outputApiFile })
    }
    tasks.register<Sync>("apiDump") {
        group = "other"
        description = "Writes the current public API to api/$apiFileName."
        from(apiBuild.flatMap { it.outputApiFile })
        into(layout.projectDirectory.dir("api"))
    }
    tasks.named("check").configure { dependsOn(apiCheck) }
}
