/**
 * @author Amit Kumar on 29/01/26
 */

package com.example.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.api.navigation.EditorFeatureEntry
import com.example.impl.data.routes.EditorFeatureRoutes
import com.example.impl.presentation.editor
import com.example.module_injector.navigation.Navigable
import javax.inject.Inject

class EditorFeatureEntryImpl @Inject constructor() : EditorFeatureEntry() {
    private lateinit var  navHostController: NavHostController
    override val featureRoute: Navigable
        get() = FeatureRootRoute.EditorRootRoute

    override fun NavGraphBuilder.navigate(navHostController: NavHostController) {
        this@EditorFeatureEntryImpl.navHostController = navHostController
        editor()
    }

    override fun navigateToEditorMainPage(param: Any?) {
        navHostController.navigate(EditorFeatureRoutes.EditorMainPage)
    }
}
