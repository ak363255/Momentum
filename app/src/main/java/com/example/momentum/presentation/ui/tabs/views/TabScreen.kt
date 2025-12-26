/**
 * @author Amit Kumar on 27/12/25
 */

package com.example.momentum.presentation.ui.tabs.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.module_injector.navigation.NavigableRoutes
import com.example.momentum.di.modules.GlobalNavigationProvider
import com.example.momentum.presentation.ui.MainNavGraph
import com.example.momentum.presentation.ui.tabs.viewmodel.TabScreenEffect
import com.example.momentum.presentation.ui.tabs.viewmodel.TabScreenViewModel
import com.example.utils.platform.screen.ScreenContent

@Composable
fun TabScreen(modifier: Modifier = Modifier, globalNavigationProvider: GlobalNavigationProvider) {
    val tabScreenViewModel: TabScreenViewModel = viewModel()
    ScreenContent(contractProvider = tabScreenViewModel) { state ->
        val navController = rememberNavController()
        Scaffold(
            modifier = modifier.fillMaxSize(),
            bottomBar = {
                TabBottomBar(selectedItem = state.currentTab) {event ->
                    dispatchEvent(event)
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                MainNavGraph(globalNavigationProvider,navController)
            }
        }
        collectEffect { effect ->
            when(effect){
                TabScreenEffect.ShowAnalyticsFeature -> navController.navigate(NavigableRoutes.AnalyticsPage)
                TabScreenEffect.ShowCategoriesFeature -> navController.navigate(NavigableRoutes.CategoriesPage)
                TabScreenEffect.ShowHomeFeature -> navController.navigate(NavigableRoutes.MainPage)
                TabScreenEffect.ShowOverviewFeature -> navController.navigate(NavigableRoutes.OverviewPage)
                TabScreenEffect.ShowSettingsFeature -> navController.navigate(NavigableRoutes.SettingsPage)
                TabScreenEffect.ShowTemplateFeature -> navController.navigate(NavigableRoutes.TemplatePage)
            }
        }
    }

}