/**
 * @author Amit Kumar on 05/01/26
 */

package com.example.impl.di.module

import com.example.impl.common.HomeEitherWrapper
import com.example.impl.common.HomeErrorHandler
import com.example.impl.domain.interactors.ScheduleInteractor
import com.example.impl.presentation.mappers.schedule.TimeTaskDomainToUiMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal interface DomainModule {

    @Binds
    @Singleton
    fun providesScheduleInteractor(scheduleInteractor: ScheduleInteractor.Base
    ): ScheduleInteractor

    @Binds
    @Singleton
    fun providesHomeErrorHandler(homeErrorHandler: HomeErrorHandler.Base): HomeErrorHandler

    @Binds
    @Singleton
    fun providesHomeEitherWrapper(homeEitherWrapper: HomeEitherWrapper.Base): HomeEitherWrapper

    @Binds
    @Singleton
    fun providesTimeTaskDomainToUiMapper(timeTaskDomainToUiMapper: TimeTaskDomainToUiMapper.Base): TimeTaskDomainToUiMapper
}