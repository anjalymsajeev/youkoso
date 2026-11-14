import com.android.build.api.dsl.CommonExtension
import io.github.anjalymsajeev.youkoso.buildlogic.library
import io.github.anjalymsajeev.youkoso.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Adds Jetpack Compose to an Android library or application module. Apply it together with
 * `youkoso.android.library` or `youkoso.android.application`.
 */
class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

        extensions.getByType<CommonExtension>().buildFeatures.compose = true

        dependencies {
            val bom = platform(libs.library("androidx-compose-bom"))
            add("implementation", bom)
            add("testImplementation", bom)
            add("androidTestImplementation", bom)
            // The Compose compiler plugin requires the runtime on every Compose module's classpath.
            add("implementation", libs.library("androidx-compose-runtime"))
            // Compose-specific API and usage checks; build time only, never shipped.
            add("lintChecks", libs.library("compose-lints"))
        }
    }
}
