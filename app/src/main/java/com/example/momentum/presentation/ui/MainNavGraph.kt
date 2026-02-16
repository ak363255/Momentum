package com.example.momentum.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.momentum.di.modules.FeatureEntryProvider
import com.example.momentum.routes.AppRoutes

@Composable
fun MainTabNavGraph(
    featureEntry: FeatureEntryProvider,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = FeatureRootRoute.HomeRootRoute
    ) {
        with(featureEntry.homeEntry) {
            navigate(navController){navigable,navOptionBuilder ->
                navController.navigate(navigable,navOptionBuilder)
            }
        }
        with(featureEntry.settingsEntry) {
            navigate(navController){navigable,navOptionBuilder ->
                navController.navigate(navigable,navOptionBuilder)
            }
        }
        // change below  ->navigate to actual feature screen using feature starter like settings feature
    }
}