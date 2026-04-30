/**
 * @author Amit Kumar on 25/04/26
 */

package com.example.impl.navigation

import com.example.api.navigation.HomeFeatureApi
import com.example.api.navigation.HomeFeatureStarterApi
import javax.inject.Inject

internal class HomeFeatureApiImpl @Inject constructor(private val homeFeatureStarterApi: HomeFeatureStarterApi) : HomeFeatureApi {
    override fun getHomeFeatureStarter(): HomeFeatureStarterApi = homeFeatureStarterApi
}