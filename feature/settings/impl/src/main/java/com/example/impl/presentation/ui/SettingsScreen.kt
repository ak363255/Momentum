package com.example.impl.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.impl.navigation.SettingFeatureRoutes
import com.example.module_injector.FeatureRootRoutes

internal fun NavGraphBuilder.settingsScreen() {
    navigation<FeatureRootRoutes.SettingsFeatureRootRoute>(startDestination = SettingFeatureRoutes.SettingsMainPage) {
        composable<SettingFeatureRoutes.SettingsMainPage>(
        ) {
            SettingsMainPage()
        }
        composable<SettingFeatureRoutes.SettingThemePage> {
            SettingsThemePage()
        }
    }
}

@Composable
internal fun SettingsMainPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Yellow)
            .clickable {

            }) {
    }
}

@Composable
internal fun SettingsThemePage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray)
            .clickable {}) {

    }
}