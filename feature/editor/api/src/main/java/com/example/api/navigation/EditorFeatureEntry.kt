/**
 * @author Amit Kumar on 29/01/26
 */

package com.example.api.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavHostController
import com.example.module_injector.AggregateFeatureEntry
import dagger.assisted.AssistedFactory

abstract class EditorFeatureEntry: AggregateFeatureEntry {
    override val arguments: List<NamedNavArgument>
        get() = super.arguments
    override val deepLinks: List<NavDeepLink>
        get() = super.deepLinks

   abstract fun navigateToEditorMainPage(param : Any? = null)
}
