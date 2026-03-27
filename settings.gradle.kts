import com.android.build.api.dsl.SettingsExtension

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("com.android.settings") version "8.9.1"
}

rootProject.name = "idrugconnections-android"

include(":ui")

configure<SettingsExtension> {
    compileSdk = 36
    minSdk = 24
    ndkVersion = "26.1.10909125"
}
