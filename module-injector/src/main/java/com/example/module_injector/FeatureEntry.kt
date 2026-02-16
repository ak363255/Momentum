/**
 * @author Amit Kumar on 25/12/25
 */

package com.example.module_injector

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.module_injector.navigation.Navigable
import com.example.module_injector.navigation.OnNavigateTo


typealias Destinations = Map<Class<out FeatureEntry>, FeatureEntry>

interface FeatureEntry{

    val featureRoute : Navigable

    val arguments : List<NamedNavArgument>
        get() = emptyList()

    val deepLinks : List<NavDeepLink>
        get() = emptyList()
}

interface AggregateFeatureEntry: FeatureEntry{
    fun NavGraphBuilder.navigate(navHostController: NavHostController,onNavigateTo: OnNavigateTo)
}

inline fun  <reified T: FeatureEntry>Destinations.find() : T = findOrNull() ?: error("Unable to find '${T::class.java}' destination.")

inline fun <reified T: FeatureEntry>Destinations.findOrNull() : T? =  this[T::class.java] as? T