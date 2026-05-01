/**
 * @author Amit Kumar on 29/01/26
 */

package com.example.impl.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.impl.navigation.EditorFeatureRoutes
import com.example.module_injector.FeatureRootRoutes

internal fun NavGraphBuilder.editor() {
    navigation<FeatureRootRoutes.EditorFeatureRootRoute>(startDestination = EditorFeatureRoutes.EditorRoute::class) {
        composable<EditorFeatureRoutes.EditorRoute> {
            EditorScreen()
        }
    }
}

@Composable
fun EditorScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Hello Editor", color = MaterialTheme.colorScheme.onSurface)
    }
}
