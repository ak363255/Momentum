/**
 * @author Amit Kumar on 27/12/25
 */

package com.example.impl
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.api.navigation.HomeFeatureEntry
import com.example.impl.presentation.views.home
import javax.inject.Inject

class HomeFeatureEntryImpl @Inject constructor():  HomeFeatureEntry(){
    override fun NavGraphBuilder.navigate(navHostController: NavHostController) {
        home(navHostController)
    }
}