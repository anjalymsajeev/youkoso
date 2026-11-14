import com.android.build.api.dsl.ApplicationExtension
import io.github.anjalymsajeev.youkoso.buildlogic.configureJava
import io.github.anjalymsajeev.youkoso.buildlogic.configureKotlinCompiler
import io.github.anjalymsajeev.youkoso.buildlogic.configureLint
import io.github.anjalymsajeev.youkoso.buildlogic.configureSpotless
import io.github.anjalymsajeev.youkoso.buildlogic.intVersionOf
import io.github.anjalymsajeev.youkoso.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/** The sample application. Not published, so no explicit API mode, ABI dump or Dokka. */
class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.android.application")

        extensions.configure<ApplicationExtension> {
            compileSdk = libs.intVersionOf("compileSdk")
            defaultConfig {
                minSdk = libs.intVersionOf("minSdk")
                targetSdk = libs.intVersionOf("targetSdk")
            }
            buildTypes {
                release {
                    // R8 runs on the sample so consumer-facing shrinking problems show up early.
                    isMinifyEnabled = true
                    proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
                }
            }
            compileOptions.configureJava(this@with)
            lint.configureLint()
        }

        configureKotlinCompiler()
        configureSpotless()
    }
}
