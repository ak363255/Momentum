/**
 * @author Amit Kumar on 04/01/26
 */

package com.example.data.repository.schedule

import com.example.data.datasources.schedule.ScheduleLocalDataSource
import com.example.data.mappers.schedule.mapToData
import com.example.data.mappers.schedule.mapToDomain
import com.example.domain.models.schedule.TimeTask
import com.example.domain.repository.schedule.TimeTaskRepository
import kotlinx.coroutines.flow.first
import java.util.Date
import javax.inject.Inject

class TimeTaskRepositoryImpl @Inject constructor(
    private val scheduleLocalDataSource: ScheduleLocalDataSource
) : TimeTaskRepository {
    override suspend fun updateTimeTasks(timeTasks: List<TimeTask>) {
        scheduleLocalDataSource.updateTimeTasks(timeTasks.map { it.mapToData() })
    }

    override suspend fun addTimeTasks(timeTasks: List<TimeTask>) {
        scheduleLocalDataSource.addTimeTasks(timeTasks.map { it.mapToData() })
    }

    override suspend fun fetchAllTimeTaskByDate(date: Date): List<TimeTask> {
        return scheduleLocalDataSource.fetchScheduleByDate(date = date.time).first().let { scheduleDetail ->
            scheduleDetail?.timeTasks?.map { it.mapToDomain() }?:emptyList()
        }
    }

    override suspend fun updateTimeTask(timeTask: TimeTask) {
        scheduleLocalDataSource.updateTimeTasks(listOf(timeTask.mapToData()))
    }

    override suspend fun removeTimeTaskByKey(keys: List<Long>) {
        scheduleLocalDataSource.removeTimeTasksByKey(keys)
    }
}