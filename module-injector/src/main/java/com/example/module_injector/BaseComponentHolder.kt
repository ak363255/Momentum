/**
 * @author Amit Kumar on 21/12/25
 */

package com.example.module_injector

interface BaseComponentHolder<A: BaseFeatureApi,D: BaseFeatureDependencies> {

    fun initD(dep : D)

    fun fetchApi():A

    fun clear()
}