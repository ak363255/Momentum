package com.example.impl.presentation.models.editmodel

import com.example.domain.models.schedule.TaskPriority

data class EditParameters(
    val priority : TaskPriority = TaskPriority.STANDARD,
    val isEnableNotification : Boolean = true,
    val taskNotification : TaskNotifications = TaskNotifications(),
    val isConsiderInStatistics : Boolean = true
)
