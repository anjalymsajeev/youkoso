plugins {
    alias(libs.plugins.youkoso.android.library)
    alias(libs.plugins.youkoso.android.compose)
}

android {
    namespace = "io.github.anjalymsajeev.youkoso.material3"
}

dependencies {
    api(projects.youkosoCompose)
}
