/**
 * @author Amit Kumar on 25/04/26
 */

package com.example.impl.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.impl.navigation.QrScanRoutes
import com.example.module_injector.FeatureRootRoutes

internal fun NavGraphBuilder.qrScanScreen() {
    navigation<FeatureRootRoutes.QrScanFeatureRootRoute>(startDestination = QrScanRoutes.QrScan) {
        composable<QrScanRoutes.QrScan> {
            QrScanScreen()
        }
    }
}

@Composable
fun QrScanScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Hello Qr Scanner")
    }
}