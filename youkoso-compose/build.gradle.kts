plugins {
    alias(libs.plugins.youkoso.android.library)
    alias(libs.plugins.youkoso.android.compose)
}

android {
    namespace = "io.github.anjalymsajeev.youkoso.compose"
}

dependencies {
    api(projects.youkosoCore)
}
