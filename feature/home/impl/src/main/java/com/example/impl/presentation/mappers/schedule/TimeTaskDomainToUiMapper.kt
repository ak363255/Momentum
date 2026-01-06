/**
 * @author Amit Kumar on 04/01/26
 */

package com.example.impl.presentation.mappers.schedule

import com.example.domain.common.TimeTaskStatusChecker
import com.example.domain.models.schedule.TimeTask
import com.example.domain.models.schedule.TimeTaskNotification
import com.example.impl.presentation.mappers.category.mapToDomain
import com.example.impl.presentation.mappers.category.mapToUi
import com.example.impl.presentation.model.schedule.TimeTaskNotificationUi
import com.example.impl.presentation.model.schedule.TimeTaskUi
import com.example.utils.extensions.duration
import com.example.utils.functional.ParametrizedMapper
import com.example.utils.managers.DateManager
import javax.inject.Inject

internal interface TimeTaskDomainToUiMapper : ParametrizedMapper<TimeTask, TimeTaskUi, Boolean>{

    class Base @Inject constructor(
        private val dateManager: DateManager,
        private val timeTaskStatusChecker : TimeTaskStatusChecker
    ) : TimeTaskDomainToUiMapper{
        override fun map(input: TimeTask,param : Boolean): TimeTaskUi {
            return TimeTaskUi(
                key = input.key,
                date = input.date,
                createdAt = input.createdAt,
                startTime = input.timeRange.from,
                endTime = input.timeRange.to,
                leftTime = dateManager.calculateLeftTime(input.timeRange.to),
                priority = input.priority,
                note = input.note,
                isCompleted = input.isCompleted,
                progress = dateManager.calculateProgress(input.timeRange.from, input.timeRange.to),
                isEnableNotification = input.isEnableNotification,
                isConsiderInStatistics = input.isConsiderInStatistics,
                isTemplate = param,
                duration = duration(input.timeRange),
                executionStatus = timeTaskStatusChecker.fetchStatus(input.timeRange,dateManager.fetchCurrentDate()),
                mainCategory = input.mainCategory.mapToUi(),
                subCategory = input.subCategory?.mapToUi(),
                timeTaskNotification = input.notificationTasks.mapToUi()
            )
        }
    }
}

internal fun TimeTaskUi.mapToDomain() = TimeTask(
    key = this.key,
    date = this.date,
    createdAt = this.createdAt,
    mainCategory = this.mainCategory.mapToDomain(),
    subCategory = this.subCategory?.mapToDomain(),
    isEnableNotification = this.isEnableNotification,
    notificationTasks = this.timeTaskNotification.mapToDomain(),
    isConsiderInStatistics = this.isConsiderInStatistics,
    timeRange = this.timeToRange(),
    note = this.note,
    priority = this.priority,
    isCompleted = this.isCompleted
)

internal fun TimeTaskNotification.mapToUi() = TimeTaskNotificationUi(
    fifteenMinutesBeforeNotify = this.fifteenMinBefore,
    oneHourBeforeNotify = this.oneHourBefore,
    threeHourBeforeNotify = this.threeHourBefore,
    oneDayBeforeNotify = this.oneDayBefore,
    oneWeekBeforeNotify = this.oneWeekBefore,
    beforeEnd = this.beforeEnd
)

internal fun TimeTaskNotificationUi.mapToDomain() = TimeTaskNotification(
    fifteenMinBefore = this.fifteenMinutesBeforeNotify,
    oneHourBefore = this.oneHourBeforeNotify,
    threeHourBefore = this.threeHourBeforeNotify,
    oneDayBefore = this.oneDayBeforeNotify,
    oneWeekBefore = this.oneWeekBeforeNotify,
    beforeEnd = this.beforeEnd
)