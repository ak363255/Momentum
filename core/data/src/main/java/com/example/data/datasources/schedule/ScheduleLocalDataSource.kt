/**
 * @author Amit Kumar on 02/01/26
 */

package com.example.data.datasources.schedule

import com.example.data.models.schedules.DailyScheduleEntity
import com.example.data.models.schedules.ScheduleDetails
import com.example.data.models.task.TimeTaskEntity
import com.example.utils.functional.TimeRange
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

interface ScheduleLocalDataSource {

    suspend fun addSchedules(
        dailySchedule: List<DailyScheduleEntity>,
        timeTask: List<TimeTaskEntity>
    )

    suspend fun addTimeTasks(timeTasks: List<TimeTaskEntity>)

    fun fetchScheduleByDate(date: Long): Flow<ScheduleDetails>

    fun fetchScheduleByRange(range: TimeRange?): Flow<List<ScheduleDetails>>

    suspend fun updateTimeTasks(timeTasks: List<TimeTaskEntity>)

    suspend fun removeDailySchedule(schedule: DailyScheduleEntity)

    suspend fun removeAllSchedules(): List<ScheduleDetails>

    suspend fun removeTimeTasksByKey(keys: List<Long>)


    class Base @Inject constructor(val scheduleDao: ScheduleDao) : ScheduleLocalDataSource {
        override suspend fun addSchedules(
            dailySchedule: List<DailyScheduleEntity>,
            timeTask: List<TimeTaskEntity>
        ) {
            scheduleDao.addDailSchedules(dailySchedule)
            scheduleDao.addTimeTasks(timeTask)
        }

        override suspend fun addTimeTasks(timeTasks: List<TimeTaskEntity>) {
            scheduleDao.addTimeTasks(timeTasks)
        }

        override fun fetchScheduleByDate(date: Long): Flow<ScheduleDetails> {
            return scheduleDao.fetchScheduleByDate(date)
        }

        override fun fetchScheduleByRange(range: TimeRange?): Flow<List<ScheduleDetails>> {
            return if (range == null) {
                scheduleDao.fetchAllSchedules()
            } else {
                scheduleDao.fetchScheduleByDateRange(range.from.time, range.to.time)
            }
        }

        override suspend fun updateTimeTasks(timeTasks: List<TimeTaskEntity>) {
            scheduleDao.updateTimeTasks(timeTasks)
        }

        override suspend fun removeDailySchedule(schedule: DailyScheduleEntity) {
             scheduleDao.removeDailSchedule(schedule)
        }

        override suspend fun removeAllSchedules(): List<ScheduleDetails> {
            val deletableSchedules = scheduleDao.fetchAllSchedules().first()
            scheduleDao.removeAllDailySchedules()
            return deletableSchedules
        }

        override suspend fun removeTimeTasksByKey(keys: List<Long>) {
                scheduleDao.removeTimeTaskByKey(keys)
        }

    }
}