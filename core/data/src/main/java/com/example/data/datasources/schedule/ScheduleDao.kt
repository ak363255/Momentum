package com.example.data.datasources.schedule

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.models.schedules.DailyScheduleEntity
import com.example.data.models.schedules.ScheduleDetails
import com.example.data.models.task.TimeTaskEntity
import kotlinx.coroutines.flow.Flow

/**
 * @author Amit Kumar on 01/01/26
 */
@Dao
interface ScheduleDao {

    @Query("SELECT * FROM dailySchedules")
     fun fetchAllSchedules(): Flow<List<ScheduleDetails>>

    @Query("SELECT * FROM dailySchedules WHERE date = :date")
    fun fetchScheduleByDate(date: Long): Flow<ScheduleDetails?>

    @Query("SELECT * FROM dailySchedules WHERE date >= :from AND date <= :to")
    fun fetchScheduleByDateRange(from: Long, to: Long): Flow<List<ScheduleDetails>>

    @Insert(entity = DailyScheduleEntity::class)
    suspend fun addDailSchedules(schedules: List<DailyScheduleEntity>)

    @Insert(entity = TimeTaskEntity::class)
    suspend fun addTimeTasks(timeTasks: List<TimeTaskEntity>)

    @Update
    suspend fun updateTimeTasks(timeTasks : List<TimeTaskEntity>)

    @Delete
    suspend fun removeDailSchedule(schedule : DailyScheduleEntity)

    @Query("DELETE FROM dailySchedules")
    suspend fun removeAllDailySchedules()

    @Query("DELETE FROM timeTasks where `key`In (:key) ")
    suspend fun removeTimeTaskByKey(key : List<Long>)
}