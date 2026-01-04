package com.example.impl.presentation.model.schedule

import com.example.domain.models.schedule.DailScheduleStatus
import java.util.Date

internal data class ScheduleUi(
    val date: Date,
    val timeTasks: List<TimeTaskUi>,
    val status: DailScheduleStatus,
    val progress: Float
)
