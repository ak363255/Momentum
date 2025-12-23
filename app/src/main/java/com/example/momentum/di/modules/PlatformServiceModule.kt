/**
 * @author Amit Kumar on 21/12/25
 */

package com.example.momentum.di.modules

import com.example.momentum.data.AnalyticsServiceImpl
import com.example.momentum.data.AppServiceImpl
import com.example.momentum.data.CrashlyticsServiceImpl
import com.example.utils.platform.services.AnalyticsService
import com.example.utils.platform.services.AppService
import com.example.utils.platform.services.CrashlyticsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PlatformServiceModule {

    @Provides
    @Singleton
    fun provideCrashlyticsService(): CrashlyticsService = CrashlyticsServiceImpl()

    @Provides
    @Singleton
    fun provideAnalyticsService() : AnalyticsService = AnalyticsServiceImpl()

    @Provides
    @Singleton
    fun provideAppService() : AppService = AppServiceImpl()

}