plugins {
    alias(libs.plugins.youkoso.android.library)
}

android {
    namespace = "io.github.anjalymsajeev.youkoso.datastore"
}

dependencies {
    api(projects.youkosoCore)
}
