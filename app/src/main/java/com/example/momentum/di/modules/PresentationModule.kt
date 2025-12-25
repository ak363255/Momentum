/**
 * @author Amit Kumar on 25/12/25
 */

package com.example.momentum.di.modules

import com.example.momentum.presentation.viewmodel.MainEffectCommunicator
import com.example.momentum.presentation.viewmodel.MainStateCommunicator
import com.example.momentum.presentation.viewmodel.SettingsWorkProcessor
import com.example.momentum.presentation.viewmodel.SettingsWorksCommand
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface PresentationModule {

    @Binds
    @Singleton
    fun bindsMainEffectCommunicator(mainEffectCommunicator: MainEffectCommunicator.Base): MainEffectCommunicator

    @Binds
    @Singleton
    fun bindsMainStateCommunicator(mainEffectCommunicator: MainStateCommunicator.Base): MainStateCommunicator

    @Binds
    @Singleton
    fun bindsSettingsWorkProcessor(settingsWorkProcessor: SettingsWorkProcessor.Base): SettingsWorkProcessor

}