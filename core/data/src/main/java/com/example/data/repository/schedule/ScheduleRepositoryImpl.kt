/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.data.repository.schedule

import com.example.data.datasources.schedule.ScheduleLocalDataSource
import com.example.data.mappers.schedule.ScheduleToDomainMapper
import com.example.data.mappers.schedule.mapToData
import com.example.data.models.task.TimeTaskEntity
import com.example.domain.models.schedule.Schedule
import com.example.domain.repository.schedule.ScheduleRepository
import com.example.utils.functional.TimeRange
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ScheduleRepositoryImpl(
    private val scheduleLocalDataSource: ScheduleLocalDataSource,
    private val scheduleToDomainMapper: ScheduleToDomainMapper
) : ScheduleRepository {
    override suspend fun createSchedule(schedules: List<Schedule>) {
        val dailySchedules = schedules.map { it.mapToData() }
        val timeTasks = mutableListOf<TimeTaskEntity>().apply {
            schedules.forEach { schedule ->
                schedule.timeTask.forEach { task ->
                    add(task.mapToData())
                }
            }
        }
        scheduleLocalDataSource.addSchedules(dailySchedules, timeTasks)
    }

    override fun fetchDailyScheduleByRange(timeRange: TimeRange?): Flow<List<Schedule>> {
        return  scheduleLocalDataSource.fetchScheduleByRange(timeRange).map { it ->
            it.map {
                scheduleToDomainMapper.map(it)
            }
        }
    }

    override fun fetchScheduleByDate(date: Long): Flow<Schedule> {
        return scheduleLocalDataSource.fetchScheduleByDate(date).map {
            scheduleToDomainMapper.map(it)
        }
    }

    override suspend fun updateSchedule(schedule: Schedule) {
        return scheduleLocalDataSource.updateTimeTasks(schedule.timeTask.map { it.mapToData() })
    }

    override suspend fun deleteAllSchedules(): List<Schedule> {
        return scheduleLocalDataSource.removeAllSchedules().map {
            scheduleToDomainMapper.map(it)
        }
    }
}