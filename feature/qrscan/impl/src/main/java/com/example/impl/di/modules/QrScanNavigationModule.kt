package com.example.impl.di.modules

import com.example.api.di.QrScanApi
import com.example.api.navigation.QrScanFeatureEntry
import com.example.impl.navigation.QrScanFeatureEntryImpl
import com.example.impl.navigation.QrScannerApiImpl
import com.example.impl.navigation.QrScannerStarterImpl
import com.example.module_injector.navigation.NavigationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object QrScanNavigationModule {
    @Provides
    fun bindQrScanApi(navigationManager: NavigationManager): QrScanApi {
        return QrScannerApiImpl(QrScannerStarterImpl(navigationManager))
    }

    @Provides
    fun bindQrFeatureEntry(): QrScanFeatureEntry {
        return QrScanFeatureEntryImpl()
    }
}