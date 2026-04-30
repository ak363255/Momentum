package com.example.momentum.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.module_injector.FeatureRootRoutes
import com.example.module_injector.navigation.NavigationManager
import com.example.momentum.di.modules.FeatureEntryProvider
import com.example.momentum.presentation.ui.tabs.views.rememberNavigator

@Composable
fun MainTabNavGraph(
    featureEntry: FeatureEntryProvider,
    navController : NavHostController,
    navigationManager: NavigationManager
) {
    val navigator = rememberNavigator(navController, navigationManager)
    NavHost(
        navController = navController,
        startDestination = FeatureRootRoutes.HomeFeatureRootRoute
    ) {
        with(featureEntry.homeEntry) {
            navigate()
        }
        with(featureEntry.editorEntry){
            navigate()
        }
        with(featureEntry.qrScanFeatureEntry){
            navigate()
        }
        with(featureEntry.settingsEntry){
            navigate()
        }
        // change below  ->navigate to actual feature screen using feature starter like settings feature
    }
}