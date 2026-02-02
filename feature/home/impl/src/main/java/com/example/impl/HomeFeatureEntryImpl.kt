/**
 * @author Amit Kumar on 27/12/25
 */

package com.example.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.api.navigation.HomeFeatureEntry
import com.example.impl.common.HomePageRoutes
import com.example.impl.presentation.views.home
import com.example.module_injector.navigation.Navigable
import javax.inject.Inject

class HomeFeatureEntryImpl @Inject  constructor() : HomeFeatureEntry() {

    private lateinit var navHostController: NavHostController

    override val featureRoute: Navigable
        get() = FeatureRootRoute.HomeRootRoute

    override fun NavGraphBuilder.navigate(navHostController: NavHostController) {
        this@HomeFeatureEntryImpl.navHostController = navHostController
        home()
    }
}