/**
 * @author Amit Kumar on 27/12/25
 */

package com.example.api.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import com.example.module_injector.AggregateFeatureEntry

abstract class HomeFeatureEntry : AggregateFeatureEntry {

    override val arguments: List<NamedNavArgument>
        get() = super.arguments

    override val deepLinks: List<NavDeepLink>
        get() = super.deepLinks

}