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
import com.example.module_injector.navigation.NavigableRoutes
import com.example.momentum.di.modules.LocalRootNavigator
import com.example.momentum.di.modules.RootNavigator
import com.example.momentum.di.modules.TabFeatureProvider

@Composable
fun MainScreen(tabFeatureProvider: TabFeatureProvider) {
    val rootNavController = rememberNavController()
    val rootNavigator = remember { RootNavigator.Base(rootNavController) }
    CompositionLocalProvider(
        LocalRootNavigator provides rootNavigator
    ) {
        NavHost(navController = rootNavController, startDestination = NavigableRoutes.Root){
            composable<NavigableRoutes.Root> {
                TabScreen(modifier = Modifier,tabFeatureProvider)
            }
        }
    }
}
