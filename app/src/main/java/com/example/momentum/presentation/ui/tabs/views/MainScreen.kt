/**
 * @author Amit Kumar on 28/12/25
 */

package com.example.momentum.presentation.ui.tabs.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.momentum.di.modules.FeatureEntryProvider
import com.example.momentum.di.modules.RootNavigatorBase
import com.example.momentum.routes.AppRoutes
import com.example.ui.views.LocalRootNavigator

@Composable
fun MainScreen(featureEntry: FeatureEntryProvider) {
    val rootNavController = rememberNavController()
    val rootNavigator = remember { RootNavigatorBase(rootNavController) }
    CompositionLocalProvider(
        LocalRootNavigator provides rootNavigator
    ) {
        NavHost(navController = rootNavController, startDestination = AppRoutes.AppRootRoutes){
            composable<AppRoutes.AppRootRoutes> {
                TabScreen(modifier = Modifier,featureEntry)
            }
            with(featureEntry.editorEntry){
                navigate(navHostController = rootNavController)
            }
        }
    }
}
