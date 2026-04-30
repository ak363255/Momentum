package com.example.impl.navigation

import androidx.navigation.NavGraphBuilder
import com.example.api.navigation.HomeFeatureEntry
import com.example.impl.presentation.views.home
import javax.inject.Inject

/**
 * @author Amit Kumar on 27/12/25
 */
internal class HomeFeatureEntryImpl @Inject constructor() : HomeFeatureEntry() {

    override fun NavGraphBuilder.navigate() {
        home()
    }
}