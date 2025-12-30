/**
 * @author Amit Kumar on 31/12/25
 */

package com.example.data.models.schedules

import androidx.room.Entity
import java.util.Date

@Entity("dailySchedules")
data class DailyScheduleEntity(
    val date : Long
)