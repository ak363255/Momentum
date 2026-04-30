/**
 * @author Amit Kumar on 25/04/26
 */

package com.example.impl.navigation

import androidx.navigation.NavGraphBuilder
import com.example.api.navigation.QrScanFeatureEntry
import com.example.impl.presentation.qrScanScreen
import javax.inject.Inject

internal class QrScanFeatureEntryImpl @Inject constructor(): QrScanFeatureEntry() {
    override fun NavGraphBuilder.navigate() {
          this.qrScanScreen()
    }
}