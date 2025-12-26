package com.example.module_injector.navigation

import kotlinx.serialization.Serializable

sealed class NavigableRoutes : Navigable {

    @Serializable
    data object EditorMainPage : NavigableRoutes()

    @Serializable
    data object AnalyticsPage : NavigableRoutes()

    @Serializable
    data object OverviewPage : NavigableRoutes()

    @Serializable
    data object SettingsPage : NavigableRoutes()

    @Serializable
    data object CategoriesPage : NavigableRoutes()

    @Serializable
    data object MainPage : NavigableRoutes()

    @Serializable
    data object TemplatePage : NavigableRoutes()

    @Serializable
    data object TabScreen : NavigableRoutes()


}