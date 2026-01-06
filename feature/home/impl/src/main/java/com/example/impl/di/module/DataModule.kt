package com.example.impl.di.module

import com.example.impl.data.repository.FeatureScheduleRepositoryImpl
import com.example.impl.domain.interactors.SettingsInteractor
import com.example.impl.domain.interactors.TimeShiftInteractor
import com.example.impl.domain.repository.FeatureScheduleRepository
import com.example.impl.presentation.mappers.schedule.ScheduleDomainToUiMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {
    @Binds
    @Singleton
    fun bindsFeatureScheduleRepository(featureScheduleRepository: FeatureScheduleRepositoryImpl): FeatureScheduleRepository


    @Binds
    @Singleton
    fun bindsSettingsInteractor(settingsInteractor: SettingsInteractor.Base): SettingsInteractor

    @Binds
    @Singleton
    fun bindsTimeShiftInteractor(timeShiftInteractor: TimeShiftInteractor.Base): TimeShiftInteractor


    @Binds
    @Singleton
    fun bindsScheduleDomainToUiMapper(scheduleDomainToUiMapper: ScheduleDomainToUiMapper.Base): ScheduleDomainToUiMapper





}