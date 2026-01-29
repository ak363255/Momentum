/**
 * @author Amit Kumar on 29/01/26
 */

package com.example.impl.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.impl.data.routes.NavigateToEditorMainPage
import com.example.module_injector.navigation.OnNavigateTo


val LocalEditorNavigator = staticCompositionLocalOf<OnNavigateTo> { throw IllegalStateException() }
internal fun NavGraphBuilder.editor() {
    composable<NavigateToEditorMainPage> {
        EditorScreen()
    }
}

@Composable
fun EditorScreen() {

}
