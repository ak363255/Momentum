/**
 * @author Amit Kumar on 29/12/25
 */

package com.example.momentum.di.modules

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.module_injector.navigation.Navigable

interface TabNavigator {
    @Composable
    fun NavigateTo(destination: Navigable,navOptions: NavOptions)

    class Base(private val navController: NavController): TabNavigator{
        @Composable
        override fun NavigateTo(destination: Navigable,navOptions : NavOptions) {
            navController.navigate(destination,navOptions)
        }
    }
}

val LocalTabNavigator = staticCompositionLocalOf<TabNavigator> { error("Please provide") }