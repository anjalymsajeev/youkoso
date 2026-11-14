import com.android.build.api.dsl.LibraryExtension
import io.github.anjalymsajeev.youkoso.buildlogic.configureAndroidAbiValidation
import io.github.anjalymsajeev.youkoso.buildlogic.configureDokka
import io.github.anjalymsajeev.youkoso.buildlogic.configureJava
import io.github.anjalymsajeev.youkoso.buildlogic.configureLint
import io.github.anjalymsajeev.youkoso.buildlogic.configurePublishedKotlinLibrary
import io.github.anjalymsajeev.youkoso.buildlogic.configureSpotless
import io.github.anjalymsajeev.youkoso.buildlogic.intVersionOf
import io.github.anjalymsajeev.youkoso.buildlogic.library
import io.github.anjalymsajeev.youkoso.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

/**
 * Published Android library. Kotlin support comes from AGP's built-in Kotlin integration,
 * so the `org.jetbrains.kotlin.android` plugin is intentionally not applied.
 */
class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.android.library")

        extensions.configure<LibraryExtension> {
            compileSdk = libs.intVersionOf("compileSdk")
            defaultConfig {
                minSdk = libs.intVersionOf("minSdk")
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            compileOptions.configureJava(this@with)
            lint.configureLint()
        }

        configurePublishedKotlinLibrary()
        configureAndroidAbiValidation()
        configureSpotless()
        configureDokka()

        dependencies {
            add("testImplementation", libs.library("junit4"))
            add("testImplementation", libs.library("kotlin-test-junit"))
        }
    }
}
