/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.impl.domain.common

import com.example.domain.models.schedule.TimeTask
import com.example.domain.models.template.Template
import com.example.impl.domain.entity.EditModel
import com.example.utils.functional.TimeRange

internal fun TimeTask.convertToEditModel(template: Template?, undefinedTaskId: Long?) = EditModel(
    key = key,
    date = date,
    startTime = timeRange.from,
    endTime = timeRange.to,
    createdAt = createdAt,
    mainCategory = mainCategory,
    subCategory = subCategory,
    priority = priority,
    isCompleted = isCompleted,
    isEnableNotification = isEnableNotification,
    taskNotifications = notificationTasks,
    isConsiderInStatistics = isConsiderInStatistics,
    repeatEnabled = template?.repeatEnabled ?: false,
    templateId = template?.templateId,
    undefinedTaskId = undefinedTaskId,
    repeatTimes = template?.repeatTimes ?: emptyList(),
    note = note,
)

internal fun EditModel.convertToTimeTask() = TimeTask(
    key = key,
    date = date,
    timeRange = TimeRange(startTime, endTime),
    createdAt = createdAt,
    mainCategory = mainCategory,
    subCategory = subCategory,
    isCompleted = isCompleted,
    priority = priority,
    isEnableNotification = isEnableNotification,
    notificationTasks = taskNotifications,
    isConsiderInStatistics = isConsiderInStatistics,
    note = note,
)