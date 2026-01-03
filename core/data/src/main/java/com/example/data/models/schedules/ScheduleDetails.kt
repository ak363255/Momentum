/**
 * @author Amit Kumar on 31/12/25
 */

package com.example.data.models.schedules

import androidx.room.Embedded
import androidx.room.Relation
import com.example.data.models.task.TimeTaskDetails
import com.example.data.models.task.TimeTaskEntity

data class ScheduleDetails(
    @Embedded
    val dailySchedule : DailyScheduleEntity,

    @Relation(
        parentColumn = "date",
        entityColumn = "dailyScheduleDate",
        entity = TimeTaskEntity::class
    )
    val timeTasks : List<TimeTaskDetails>
)