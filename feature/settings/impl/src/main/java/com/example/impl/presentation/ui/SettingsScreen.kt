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
import com.example.impl.navigation.SettingsNavigableRoutes
import com.example.module_injector.navigation.NavigableRoutes
import com.example.module_injector.navigation.OnNavigateTo

internal fun NavGraphBuilder.settingsScreen(onNavigateTo: OnNavigateTo) {
    navigation<NavigableRoutes.SettingsPage>(startDestination = SettingsNavigableRoutes.SettingsMainPage) {
        composable<SettingsNavigableRoutes.SettingsMainPage>(
        ) {
            SettingsMainPage(onNavigateTo)
        }
        composable<SettingsNavigableRoutes.SettingsThemePage> {
            SettingsThemePage(onNavigateTo)
        }
    }
}

@Composable
internal fun SettingsMainPage(onNavigateTo: OnNavigateTo) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Yellow)
        .clickable {
            onNavigateTo(SettingsNavigableRoutes.SettingsThemePage) {}
        }) {

    }
}

@Composable
internal fun SettingsThemePage(onNavigateTo: OnNavigateTo) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.LightGray)
        .clickable {
            onNavigateTo(NavigableRoutes.TabScreen) {}
        }) {

    }
}