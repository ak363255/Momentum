/**
 * @author Amit Kumar on 26/12/25
 */

package com.example.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.api.navigation.SettingFeatureEntry
import javax.inject.Inject


class SettingFeatureEntryImpl @Inject constructor() : SettingFeatureEntry() {
    private val rootRoute = "@${featureRoute}"
    override fun NavGraphBuilder.navigate(
        navHostController: NavHostController,
    ) {
        navigation(startDestination = featureRoute, route = rootRoute) {
            composable(
                route = featureRoute,
                arguments = arguments,
                deepLinks = deepLinks
            ) { backStackEntry ->
            }

        }

    }

}
