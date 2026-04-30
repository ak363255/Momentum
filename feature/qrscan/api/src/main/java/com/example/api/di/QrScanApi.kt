/**
 * @author Amit Kumar on 16/04/26
 */

package com.example.api.di

import com.example.api.navigation.QrScanStarter
import com.example.module_injector.BaseFeatureApi

interface QrScanApi : BaseFeatureApi {
    fun fetchQrScanStarter(): QrScanStarter
}