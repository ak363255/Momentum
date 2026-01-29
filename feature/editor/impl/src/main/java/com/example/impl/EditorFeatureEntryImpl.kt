/**
 * @author Amit Kumar on 29/01/26
 */

package com.example.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.api.navigation.EditorFeatureEntry
import com.example.impl.data.routes.NavigateToEditorMainPage
import com.example.impl.presentation.editor
import com.example.module_injector.navigation.Navigable

class EditorFeatureEntryImpl : EditorFeatureEntry(){
    override val featureRoute: Navigable
        get() = NavigateToEditorMainPage

    override fun NavGraphBuilder.navigate(navHostController: NavHostController) {
                editor()
    }
}