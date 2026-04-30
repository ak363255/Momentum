/**
 * @author Amit Kumar on 28/12/25
 */

package com.example.momentum.presentation.ui.tabs.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.module_injector.navigation.NavigationManager
import com.example.momentum.Navigator
import com.example.momentum.di.modules.FeatureEntryProvider
import com.example.momentum.routes.AppRoutes

@Composable
fun MainScreen(featureEntry: FeatureEntryProvider, navigationManager: NavigationManager) {
    TabScreen(modifier = Modifier, featureEntry,navigationManager)
}

@Composable
fun rememberNavigator(
    navController: NavController,
    navigationManager: NavigationManager
): Navigator {
    val lifeCycleOwner = LocalLifecycleOwner.current
    return remember {
        Navigator(lifeCycleOwner, navController, navigationManager)
    }
}
