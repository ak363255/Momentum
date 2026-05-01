/**
 * @author Amit Kumar on 25/04/26
 */

package com.example.impl.navigation

import com.example.api.navigation.EditorFeatureStarterApi
import com.example.api.navigation.EditorScreens
import com.example.impl.domain.interactors.EditorInteractor
import com.example.module_injector.navigation.NavigationManager
import javax.inject.Inject

internal class EditorFeatureStarterApiImpl @Inject constructor(
    private val navigationManager: NavigationManager,
): EditorFeatureStarterApi {
    override suspend fun navigateToEditorScreen(editor : EditorScreens.Editor) {
        navigationManager.navigate(EditorFeatureRoutes.EditorRoute)
    }
}