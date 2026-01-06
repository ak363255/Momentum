/**
 * @author Amit Kumar on 23/12/25
 */

package com.example.momentum.di.modules

import com.example.data.mappers.schedule.ScheduleToDomainMapper
import com.example.data.repository.schedule.ScheduleRepositoryImpl
import com.example.data.repository.schedule.TimeTaskRepositoryImpl
import com.example.data.repository.setting.ThemeSettingsRepositoryImpl
import com.example.domain.repository.schedule.ScheduleRepository
import com.example.domain.repository.schedule.TimeTaskRepository
import com.example.domain.repository.settings.ThemeSettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface DataModule {

    @Binds
    @Singleton
    fun bindThemeSettingRepository(themeSettingsRepository: ThemeSettingsRepositoryImpl): ThemeSettingsRepository

    @Binds
    @Singleton
    fun bindsScheduleRepository(scheduleRepository: ScheduleRepositoryImpl): ScheduleRepository

    @Binds
    @Singleton
    fun bindsScheduleToDomainMapper(scheduleToDomainMapper: ScheduleToDomainMapper.Base): ScheduleToDomainMapper

    @Binds
    @Singleton
    fun bindsTimeTaskRepository(timeTaskRepository: TimeTaskRepositoryImpl): TimeTaskRepository

}