/**
 * @author Amit Kumar on 16/04/26
 */

package com.example.impl.navigation

import com.example.api.navigation.QrScanStarter
import com.example.module_injector.navigation.NavigationManager
import javax.inject.Inject

internal class QrScannerStarterImpl @Inject constructor(
    private val navigationManager: NavigationManager
): QrScanStarter {

    override suspend fun navigateToQrScan() {
          navigationManager.navigate(QrScanRoutes.QrScan,null)
    }
}