/**
 * @author Amit Kumar on 23/12/25
 */

package com.example.momentum.di.modules

import com.example.momentum.domain.interactors.SettingInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface DomainModule {

    @Singleton
    @Binds
    fun bindSettingsInteractor(settingInteractor: SettingInteractor.Base): SettingInteractor
}