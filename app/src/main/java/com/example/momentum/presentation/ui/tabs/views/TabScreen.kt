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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.momentum.di.modules.FeatureEntryProvider
import com.example.momentum.di.modules.LocalTabNavigator
import com.example.momentum.di.modules.TabNavigator
import com.example.momentum.presentation.ui.MainTabNavGraph
import com.example.momentum.presentation.ui.tabs.viewmodel.TabScreenEffect
import com.example.momentum.presentation.ui.tabs.viewmodel.TabScreenEvent
import com.example.momentum.presentation.ui.tabs.viewmodel.TabScreenViewModel
import com.example.momentum.routes.AppRoutes
import com.example.utils.managers.rememberDrawerManager
import com.example.utils.platform.screen.ScreenContent

@Composable
fun TabScreen(
    modifier: Modifier = Modifier,
    featureEntry: FeatureEntryProvider,
) {
    val tabScreenViewModel: TabScreenViewModel = hiltViewModel()
    ScreenContent(contractProvider = tabScreenViewModel) { state ->
        val navController = rememberNavController()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val drawerManager = rememberDrawerManager(drawerState)
        val tabNavigator = TabNavigator.Base(navController)
        CompositionLocalProvider(
            LocalTabNavigator provides tabNavigator
        ) {
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
                            MainTabNavGraph(featureEntry, navController)
                        }
                    }
                }
            )
        }
        collectEffect { effect ->
            when (effect) {
                TabScreenEffect.ShowAnalyticsFeature -> {}
                TabScreenEffect.ShowCategoriesFeature -> {}
                TabScreenEffect.ShowHomeFeature -> navController.navigate(FeatureRootRoute.HomeRootRoute)
                TabScreenEffect.ShowOverviewFeature -> {}
                TabScreenEffect.ShowSettingsFeature ->{navController.navigate(FeatureRootRoute.SettingRootRoute)}
                TabScreenEffect.ShowTemplateFeature -> {}
            }
        }
    }

}