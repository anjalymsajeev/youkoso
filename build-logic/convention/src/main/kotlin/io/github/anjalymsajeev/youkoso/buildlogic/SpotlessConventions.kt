package io.github.anjalymsajeev.youkoso.buildlogic

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/** Formatting via ktlint. Rules come from the root `.editorconfig`. */
internal fun Project.configureSpotless() {
    pluginManager.apply("com.diffplug.spotless")
    val ktlintVersion = libs.versionOf("ktlint")
    extensions.configure<SpotlessExtension> {
        kotlin {
            target("src/**/*.kt")
            ktlint(ktlintVersion)
        }
        kotlinGradle {
            target("*.gradle.kts")
            ktlint(ktlintVersion)
        }
    }
}
