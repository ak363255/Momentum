/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.data.mappers.schedule

import com.example.data.mappers.categories.mapToDomain
import com.example.data.models.task.TimeTaskDetails
import com.example.data.models.task.TimeTaskEntity
import com.example.domain.models.schedule.TimeTask
import com.example.utils.extensions.isCurrentDay
import com.example.utils.extensions.mapToDate
import com.example.utils.extensions.shiftDay
import com.example.utils.functional.TimeRange
import java.util.Date


fun TimeTask.mapToData() = TimeTaskEntity(
    key = key,
    dailyScheduleDate = date.time,
    nextScheduleDate = if(timeRange.to.isCurrentDay()) null else date.shiftDay(1).time,
    startTime = this.timeRange.from.time,
    endTime = this.timeRange.to.time,
    createdAt = createdAt.time,
    mainCategoryId = mainCategory.categoryId,
    subCategoryId = subCategory?.id,
    isCompleted = isCompleted,
    priority = priority.ordinal,
    isEnableNotification = isEnableNotification,
    note = note,
    isConsiderInStatistics = isConsiderInStatistics,
    fifteenMinBeforeNotify = notificationTasks.fifteenMinBefore,
    oneHourBeforeNotify = notificationTasks.oneHourBefore,
    threeHourBeforeNotify = notificationTasks.threeHourBefore,
    oneDayBeforeNotify = notificationTasks.oneDayBefore,
    oneWeekBeforeNotify = notificationTasks.oneWeekBefore,
    dayBeforeNotify = notificationTasks.beforeEnd
)

fun TimeTaskDetails.mapToDomain() : TimeTask{
    val timeTaskEntity = this.timeTask
    val mainCategoryEntity = this.mainCategory.mainCategory
    val subCategoryEntity = this.subCategory
    return TimeTask(
        key = timeTaskEntity.key,
        date = timeTaskEntity.dailyScheduleDate.mapToDate(),
        createdAt = timeTaskEntity.createdAt?.mapToDate() ?: Date(),
        mainCategory = mainCategoryEntity.mapToDomain(),
        subCategory = subCategoryEntity?.mapToDomain(mainCategoryEntity.mapToDomain()),
        timeRange = TimeRange(
            timeTaskEntity.startTime.mapToDate(),
            timeTaskEntity.endTime.mapToDate()
        ),
        isEnableNotification = timeTaskEntity.isEnableNotification,
        notificationTasks = com.example.domain.models.schedule.TimeTaskNotification(
            fifteenMinBefore = timeTaskEntity.fifteenMinBeforeNotify,
            oneHourBefore = timeTaskEntity.oneHourBeforeNotify,
            threeHourBefore = timeTaskEntity.threeHourBeforeNotify,
            oneDayBefore = timeTaskEntity.oneDayBeforeNotify,
            oneWeekBefore = timeTaskEntity.oneWeekBeforeNotify,
            beforeEnd = timeTaskEntity.dayBeforeNotify
        ),
        isConsiderInStatistics = timeTaskEntity.isConsiderInStatistics,
        note = timeTaskEntity.note,
        priority = com.example.domain.models.schedule.TaskPriority.values()[timeTaskEntity.priority],
        isCompleted = timeTaskEntity.isCompleted
    )
}
