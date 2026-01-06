package com.example.impl.di.module

import com.example.impl.domain.interactors.SettingsInteractor
import com.example.impl.presentation.viewmodel.contract.HomeEffectCommunicator
import com.example.impl.presentation.viewmodel.contract.HomeStateCommunicator
import com.example.impl.presentation.viewmodel.processor.ScheduleWorkProcessor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal interface PresentationModule {

    @Binds
    @Singleton
    fun bindsScheduleWorkProcessor(scheduleWorkProcessor: ScheduleWorkProcessor.Base): ScheduleWorkProcessor

    @Binds
    @Singleton
    fun bindsHomeEffectCommunicator(homeEffectCommunicator: HomeEffectCommunicator.Base): HomeEffectCommunicator


    @Binds
    @Singleton
    fun bindsHomeStateCommunicator(homeStateCommunicator: HomeStateCommunicator.Base): HomeStateCommunicator


}