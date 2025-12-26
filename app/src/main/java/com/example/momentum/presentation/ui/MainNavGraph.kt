package com.example.momentum.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.module_injector.navigation.NavGraph
import com.example.module_injector.navigation.NavigableRoutes
import com.example.momentum.di.modules.GlobalNavigationProvider

@Composable
fun MainNavGraph(
    globalNavigationProvider : GlobalNavigationProvider
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigableRoutes.TabScreen
    ){
        composable<NavigableRoutes.TabScreen> {
            Box(modifier = Modifier.fillMaxSize().background(color = Color.Green).clickable{
                navController.navigate(NavGraph.Settings)
            })
        }
        with(globalNavigationProvider.provideSettingFeature()){
            navigate(navController)
        }
    }
}