plugins {
    alias(libs.plugins.youkoso.android.application)
    alias(libs.plugins.youkoso.android.compose)
}

android {
    namespace = "io.github.anjalymsajeev.youkoso.sample"
    defaultConfig {
        applicationId = "io.github.anjalymsajeev.youkoso.sample"
        versionCode = 1
        versionName = "0.1.0"
    }
}

dependencies {
    implementation(projects.youkosoMaterial3)
    implementation(projects.youkosoDatastore)

    implementation(libs.androidx.activity.compose)
    // activity-compose brings lifecycle 2.9.x, whose bundled lint checks are incompatible with
    // the current lint (ObsoleteLintCustomCheck). Use the latest stable lifecycle in the sample.
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
}
