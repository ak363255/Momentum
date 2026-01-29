/**
 * @author Amit Kumar on 29/01/26
 */

package com.example.impl

import androidx.compose.runtime.Composable
import com.example.api.navigation.EditorFeatureNavigator
import com.example.impl.data.routes.NavigateToEditorMainPage
import com.example.ui.views.LocalRootNavigator

class EditorFeatureNavigatorImpl : EditorFeatureNavigator {
    @Composable
    override fun NavigateToEditorMainPage() {
        val navController = LocalRootNavigator.current.provideRootNavHostController()
        navController.navigate(NavigateToEditorMainPage)
    }
}