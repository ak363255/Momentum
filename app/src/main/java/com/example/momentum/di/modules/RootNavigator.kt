/**
 * @author Amit Kumar on 29/12/25
 */

package com.example.momentum.di.modules

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.ui.views.RootNavigator


class RootNavigatorBase(private val navController: NavController): RootNavigator {
    @Composable
    override fun provideRootNavHostController(): NavController {
        return navController
    }
}


