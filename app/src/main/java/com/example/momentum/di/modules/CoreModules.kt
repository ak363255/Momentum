/**
 * @author Amit Kumar on 23/12/25
 */

package com.example.momentum.di.modules

import com.example.domain.common.ScheduleStatusChecker
import com.example.domain.common.TimeTaskStatusChecker
import com.example.utils.managers.CoroutineManager
import com.example.utils.managers.DateManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoreModules {

    @Singleton
    @Binds
    fun bindCoroutineManager(coroutineManager: CoroutineManager.Base): CoroutineManager

    @Singleton
    @Binds
    fun bindsScheduleStatusChecker(scheduleStatusChecker: ScheduleStatusChecker.Base): ScheduleStatusChecker

    @Singleton
    @Binds
    fun bindsTimeTaskStatusChecker(timeTaskStatusChecker: TimeTaskStatusChecker.Base): TimeTaskStatusChecker

    @Singleton
    @Binds
    fun bindsDateManager(dateManager: DateManager.Base): DateManager




}