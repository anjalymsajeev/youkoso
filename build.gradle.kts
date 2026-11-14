plugins {
    // Declared once here so every module shares one plugin classloader.
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.bcv) apply false
    alias(libs.plugins.spotless)
    alias(libs.plugins.dokka)
}

dokka {
    moduleName.set("youkoso")
}

dependencies {
    dokka(projects.youkosoCore)
    dokka(projects.youkosoCompose)
    dokka(projects.youkosoMaterial3)
    dokka(projects.youkosoDatastore)
}

spotless {
    val ktlintVersion = libs.versions.ktlint.get()
    kotlin {
        target("build-logic/**/src/**/*.kt")
        ktlint(ktlintVersion)
    }
    kotlinGradle {
        target("*.gradle.kts", "build-logic/**/*.gradle.kts")
        targetExclude("build-logic/**/build/**")
        ktlint(ktlintVersion)
    }
}
