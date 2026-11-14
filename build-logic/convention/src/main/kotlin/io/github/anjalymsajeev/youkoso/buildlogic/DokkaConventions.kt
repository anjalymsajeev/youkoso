package io.github.anjalymsajeev.youkoso.buildlogic

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.dokka.gradle.DokkaExtension

/**
 * API reference generation. Each library module contributes to the aggregated site built by
 * `./gradlew :dokkaGenerate` at the repository root.
 */
internal fun Project.configureDokka() {
    pluginManager.apply("org.jetbrains.dokka")
    val moduleDocs = file("Module.md")
    extensions.configure<DokkaExtension> {
        moduleName.set(project.name)
        dokkaSourceSets.configureEach {
            if (moduleDocs.exists()) {
                includes.from(moduleDocs)
            }
            sourceLink {
                localDirectory.set(file("src/main/kotlin"))
                remoteUrl("$GITHUB_REPOSITORY_URL/tree/main/${project.name}/src/main/kotlin")
                remoteLineSuffix.set("#L")
            }
        }
    }
}
