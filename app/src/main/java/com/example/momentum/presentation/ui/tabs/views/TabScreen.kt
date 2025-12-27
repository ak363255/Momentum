/**
 * @author Amit Kumar on 27/12/25
 */

package com.example.momentum.presentation.ui.tabs.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.module_injector.navigation.NavigableRoutes
import com.example.module_injector.navigation.smartNavigateTo
import com.example.momentum.di.modules.GlobalNavigationProvider
import com.example.momentum.presentation.ui.MainNavGraph
import com.example.momentum.presentation.ui.tabs.viewmodel.TabScreenEffect
import com.example.momentum.presentation.ui.tabs.viewmodel.TabScreenEvent
import com.example.momentum.presentation.ui.tabs.viewmodel.TabScreenViewModel
import com.example.utils.managers.rememberDrawerManager
import com.example.utils.platform.screen.ScreenContent

@Composable
fun TabScreen(modifier: Modifier = Modifier, globalNavigationProvider: GlobalNavigationProvider) {
    val tabScreenViewModel: TabScreenViewModel = viewModel()
    ScreenContent(contractProvider = tabScreenViewModel) { state ->
        val navController = rememberNavController()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
        val drawerManager = rememberDrawerManager(drawerState)
        HomeNavigationDrawer(
            drawerState = drawerState,
            drawerManager = drawerManager,
            modifier = Modifier,
            onItemClicked = { item ->
                val event: TabScreenEvent = when (item) {
                    HomeDrawerItems.MAIN -> {
                        TabScreenEvent.SelectedHomeTab
                    }

                    HomeDrawerItems.OVERVIEW -> {
                        TabScreenEvent.SelectedOverviewScreen
                    }

                    HomeDrawerItems.CATEGORIES -> {
                        TabScreenEvent.SelectedCategories
                    }

                    HomeDrawerItems.TEMPLATE -> {
                        TabScreenEvent.SelectedTemplateScreen
                    }
                }
                dispatchEvent(event)
            },
            content = {
                Scaffold(
                    modifier = modifier.fillMaxSize(),
                    bottomBar = {
                        TabBottomBar(selectedItem = state.currentTab) { event ->
                            dispatchEvent(event)
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        MainNavGraph(globalNavigationProvider, navController)
                    }
                }
            }
        )
        collectEffect { effect ->
            when (effect) {
                TabScreenEffect.ShowAnalyticsFeature -> {
                    navController.smartNavigateTo(NavigableRoutes.AnalyticsPage, launchSingleTop = true)
                }
                TabScreenEffect.ShowCategoriesFeature -> navController.navigate(NavigableRoutes.CategoriesPage)
                TabScreenEffect.ShowHomeFeature -> navController.navigate(NavigableRoutes.MainPage)
                TabScreenEffect.ShowOverviewFeature -> {
                    navController.smartNavigateTo(NavigableRoutes.OverviewPage, launchSingleTop = true)
                }
                TabScreenEffect.ShowSettingsFeature -> navController.navigate(NavigableRoutes.SettingsPage)
                TabScreenEffect.ShowTemplateFeature -> navController.navigate(NavigableRoutes.TemplatePage)
            }
        }
    }

}