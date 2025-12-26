package com.example.momentum.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.module_injector.navigation.NavigableRoutes
import com.example.momentum.di.modules.GlobalNavigationProvider

@Composable
fun MainNavGraph(
    globalNavigationProvider: GlobalNavigationProvider,
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = NavigableRoutes.MainPage
    ){
        composable<NavigableRoutes.MainPage> {
            Box(modifier = Modifier.fillMaxSize().background(color = Color.Green))
        }
        with(globalNavigationProvider.provideSettingFeature()){
            navigate(navController)
        }

        // change below  ->navigate to actual feature screen using feature starter like settings feature

        composable<NavigableRoutes.TemplatePage> {
            Box(modifier = Modifier.fillMaxSize().background(color = Color.Yellow))

        }
        composable<NavigableRoutes.CategoriesPage> {
            Box(modifier = Modifier.fillMaxSize().background(color = Color.Red))

        }
        composable<NavigableRoutes.OverviewPage> {
            Box(modifier = Modifier.fillMaxSize().background(color = Color.Gray))

        }
        composable<NavigableRoutes.AnalyticsPage> {
            Box(modifier = Modifier.fillMaxSize().background(color = Color.Magenta))
        }


    }
}