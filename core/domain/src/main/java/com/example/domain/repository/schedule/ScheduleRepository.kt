/**
 * @author Amit Kumar on 02/01/26
 */

package com.example.domain.repository.schedule

import com.example.domain.models.schedule.Schedule
import com.example.utils.functional.TimeRange
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {

    suspend fun createSchedule(schedules : List<Schedule>)
    fun fetchDailyScheduleByRange(timeRange : TimeRange?): Flow<List<Schedule>>
    fun fetchScheduleByDate(date : Long) : Flow<Schedule?>
    suspend fun updateSchedule(schedule: Schedule)
    suspend fun deleteAllSchedules():List<Schedule>
}