package com.example.impl.navigation

import com.example.module_injector.navigation.Navigable
import kotlinx.serialization.Serializable

internal sealed class SettingsNavigableRoutes  : Navigable{
        @Serializable
        data object SettingsMainPage : SettingsNavigableRoutes()

        @Serializable
        data object SettingsThemePage : SettingsNavigableRoutes()

}