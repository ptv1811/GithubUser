pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("com.android.settings") version "8.9.1"
}

android {
    minSdk = 25
    compileSdk = 35
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "GithubUser"
include(":app")

include(":core:common")
include(":core:network")
include(":core:database")
include(":core:model")
include(":core:testing")
include(":core:data")

include(":features:userlist")
