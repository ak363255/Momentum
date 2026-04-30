/**
 * @author Amit Kumar on 25/12/25
 */

package com.example.module_injector

import androidx.navigation.NavGraphBuilder


typealias Destinations = Map<Class<out FeatureEntry>, FeatureEntry>

interface FeatureEntry

interface AggregateFeatureEntry: FeatureEntry{
    fun NavGraphBuilder.navigate()
}

inline fun  <reified T: FeatureEntry>Destinations.find() : T = findOrNull() ?: error("Unable to find '${T::class.java}' destination.")

inline fun <reified T: FeatureEntry>Destinations.findOrNull() : T? =  this[T::class.java] as? T