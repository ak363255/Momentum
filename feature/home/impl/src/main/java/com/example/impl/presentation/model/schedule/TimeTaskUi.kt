package com.example.impl.presentation.model.schedule

import com.example.domain.models.schedule.TaskPriority
import com.example.domain.models.schedule.TimeTaskStatus
import com.example.impl.presentation.model.category.MainCategoryUi
import com.example.impl.presentation.model.category.SubCategoryUi
import com.example.utils.extensions.duration
import com.example.utils.functional.TimeRange
import java.util.Date

/**
 * @author Amit Kumar on 03/01/26
 */
internal data class TimeTaskUi(
    val key: Long,
    val date: Date,
    val createdAt: Date,
    val startTime: Date,
    val endTime: Date,
    val leftTime : Long,
    val priority: TaskPriority = TaskPriority.STANDARD,
    val note: String?,
    val isCompleted: Boolean,
    val progress: Float,
    val duration : Long = duration(TimeRange(startTime, endTime)),
    val executionStatus : TimeTaskStatus = TimeTaskStatus.PLANNED,
    val isEnableNotification : Boolean,
    val isConsiderInStatistics: Boolean,
    val isTemplate : Boolean,
    val mainCategory: MainCategoryUi,
    val subCategory: SubCategoryUi?,
    val timeTaskNotification: TimeTaskNotificationUi,
    ){
    fun timeToRange() = TimeRange(startTime, endTime)
}