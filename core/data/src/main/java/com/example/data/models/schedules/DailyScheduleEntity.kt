/**
 * @author Amit Kumar on 31/12/25
 */

package com.example.data.models.schedules

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("dailySchedules")
data class DailyScheduleEntity(
    @PrimaryKey
    @ColumnInfo("date")
    val date : Long
)