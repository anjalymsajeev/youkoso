import io.github.anjalymsajeev.youkoso.buildlogic.configureDokka
import io.github.anjalymsajeev.youkoso.buildlogic.configureJvmAbiValidation
import io.github.anjalymsajeev.youkoso.buildlogic.configurePublishedKotlinLibrary
import io.github.anjalymsajeev.youkoso.buildlogic.configureSpotless
import io.github.anjalymsajeev.youkoso.buildlogic.javaVersion
import io.github.anjalymsajeev.youkoso.buildlogic.library
import io.github.anjalymsajeev.youkoso.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

/** Pure Kotlin/JVM library with no Android dependencies (used by `youkoso-core`). */
class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("org.jetbrains.kotlin.jvm")

        extensions.configure<JavaPluginExtension> {
            sourceCompatibility = javaVersion()
            targetCompatibility = javaVersion()
        }

        configurePublishedKotlinLibrary()
        configureJvmAbiValidation()
        configureSpotless()
        configureDokka()

        dependencies {
            add("testImplementation", libs.library("junit4"))
            add("testImplementation", libs.library("kotlin-test-junit"))
        }
    }
}
