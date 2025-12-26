package com.example.module_injector.navigation

import kotlinx.serialization.Serializable

sealed class NavigableRoutes : Navigable {

    @Serializable
    data object EditorMainPage : NavigableRoutes()

    @Serializable
    data object TabScreen : NavigableRoutes()


}