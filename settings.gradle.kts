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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Momentum"
include(":app")
include(":core:data")
include(":core:utils")
include(":core:ui")
include(":core:domain")
include(":feature:settings:api")
include(":feature:settings:impl")
include(":module-injector")
include(":core:app-navigation")
include(":feature:home:api")
include(":feature:home:impl")
