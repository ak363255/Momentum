package com.example.data.datasources.schedule

import androidx.room.Dao
import androidx.room.Query
import com.example.data.models.schedules.DailyScheduleEntity

/**
 * @author Amit Kumar on 01/01/26
 */
@Dao
interface ScheduleDao {

    @Query("SELECT * FROM DAILYSCHEDULES")
    suspend fun getAllDailySchedules():List<DailyScheduleEntity>
}