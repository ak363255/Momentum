package com.example.impl.di.module

import com.example.impl.data.datasources.FeatureScheduleLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal interface DataSourceModule {

    @Binds
    @Singleton
    fun bindsFeatureScheduleLocalDataSource(featureScheduleLocalDataSource: FeatureScheduleLocalDataSource.Base): FeatureScheduleLocalDataSource

}