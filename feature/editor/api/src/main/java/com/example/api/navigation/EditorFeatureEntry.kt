/**
 * @author Amit Kumar on 29/01/26
 */

package com.example.api.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import com.example.module_injector.AggregateFeatureEntry

abstract class EditorFeatureEntry: AggregateFeatureEntry {
    override val arguments: List<NamedNavArgument>
        get() = super.arguments
    override val deepLinks: List<NavDeepLink>
        get() = super.deepLinks
}