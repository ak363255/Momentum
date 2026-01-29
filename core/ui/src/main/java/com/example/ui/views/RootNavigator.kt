/**
 * @author Amit Kumar on 29/12/25
 */

package com.example.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController

interface RootNavigator {
    @Composable
    fun  provideRootNavHostController(): NavController
}

val LocalRootNavigator = staticCompositionLocalOf<RootNavigator> { error("Please provide") }