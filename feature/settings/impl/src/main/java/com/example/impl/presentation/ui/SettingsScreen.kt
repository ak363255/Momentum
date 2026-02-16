package com.example.impl.presentation.ui

import FeatureRootRoute
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.impl.routes.SettingPageRoutes
import com.example.module_injector.navigation.OnNavigateTo

internal fun NavGraphBuilder.settingsScreen(onNavigateTo: OnNavigateTo) {
    navigation<FeatureRootRoute.SettingRootRoute>(startDestination = SettingPageRoutes.SettingsMainPage) {
        composable<SettingPageRoutes.SettingsMainPage>(
        ) {
            SettingsMainPage(onNavigateTo)
        }
        composable<SettingPageRoutes.SettingThemePage> {
            SettingsThemePage(onNavigateTo)
        }
    }
}

internal val LocalSettingsNavigator = compositionLocalOf<OnNavigateTo> { throw Exception("Error") }

@Composable
internal fun SettingsMainPage(onNavigateTo: OnNavigateTo) {
    val onNavigateTo = remember { onNavigateTo }
    CompositionLocalProvider(LocalSettingsNavigator provides onNavigateTo) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Yellow)
                .clickable {

                }) {
        }
    }

}

@Composable
internal fun SettingsThemePage(onNavigateTo: OnNavigateTo) {
    val onNavigateTo = remember {onNavigateTo}
    CompositionLocalProvider(LocalSettingsNavigator provides onNavigateTo) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.LightGray)
                .clickable {}) {

        }
    }

}