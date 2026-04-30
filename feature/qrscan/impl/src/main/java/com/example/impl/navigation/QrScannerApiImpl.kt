/**
 * @author Amit Kumar on 25/04/26
 */

package com.example.impl.navigation

import com.example.api.di.QrScanApi
import com.example.api.navigation.QrScanStarter
import javax.inject.Inject

internal class QrScannerApiImpl @Inject constructor(
    private val qrScannerStarter: QrScanStarter
): QrScanApi {
    override fun fetchQrScanStarter(): QrScanStarter = qrScannerStarter
}