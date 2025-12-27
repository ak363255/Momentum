package com.example.momentum.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.module_injector.navigation.NavigableRoutes
import com.example.momentum.di.modules.GlobalNavigationProvider
import com.example.utils.managers.LocalDrawerManager
import kotlinx.coroutines.launch

@Composable
fun MainNavGraph(
    globalNavigationProvider: GlobalNavigationProvider,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavigableRoutes.MainPage
    ) {

        with(globalNavigationProvider.provideHomeFeatureEntry()) {
            navigate(navController)
        }

        with(globalNavigationProvider.provideSettingFeature()) {
            navigate(navController)
        }

        // change below  ->navigate to actual feature screen using feature starter like settings feature

        composable<NavigableRoutes.TemplatePage> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Yellow)
            )

        }
        composable<NavigableRoutes.CategoriesPage> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Red)
            )

        }
        composable<NavigableRoutes.OverviewPage> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Gray)
            ) {
                val scope = rememberCoroutineScope()
                val drawerManager = LocalDrawerManager.current
                Column {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        modifier = Modifier
                            .size(28.dp)
                            .clickable {
                                scope.launch {
                                    drawerManager.openDrawer()
                                }
                            })
                }
            }

        }
        composable<NavigableRoutes.AnalyticsPage> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Magenta)
            )
        }


    }
}