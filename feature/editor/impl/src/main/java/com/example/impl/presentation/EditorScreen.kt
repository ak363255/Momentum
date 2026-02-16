/**
 * @author Amit Kumar on 29/01/26
 */

package com.example.impl.presentation

import FeatureRootRoute
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.impl.data.routes.EditorFeatureRoutes
import com.example.module_injector.navigation.Navigable
import com.example.module_injector.navigation.OnNavigateTo
import com.example.ui.views.LocalRootNavigator


val LocalEditorNavigator = staticCompositionLocalOf<OnNavigateTo> { throw IllegalStateException() }
internal fun NavGraphBuilder.editor(onNavigateTo: OnNavigateTo) {
    navigation<FeatureRootRoute.EditorRootRoute>(startDestination = EditorFeatureRoutes.EditorMainPage){
        composable<EditorFeatureRoutes.EditorMainPage> {
            val rootNavHostController = LocalRootNavigator.current.provideRootNavHostController()
            val onNavigateTo = remember {onNavigateTo}
            CompositionLocalProvider(LocalEditorNavigator provides onNavigateTo) {
                EditorScreen()
            }
        }
    }
}

@Composable
fun EditorScreen() {

}
