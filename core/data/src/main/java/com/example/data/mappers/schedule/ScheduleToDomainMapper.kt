/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.data.mappers.schedule

import com.example.data.models.schedules.ScheduleDetails
import com.example.domain.common.ScheduleStatusChecker
import com.example.domain.models.schedule.Schedule
import com.example.utils.extensions.mapToDate
import com.example.utils.functional.Mapper
import com.example.utils.managers.DateManager
import javax.inject.Inject

interface ScheduleToDomainMapper : Mapper<ScheduleDetails, Schedule> {
    class Base @Inject constructor(
        val scheduleStatusChecker: ScheduleStatusChecker,
        val dateManager: DateManager
    ) :
        ScheduleToDomainMapper {
        override fun map(input: ScheduleDetails): Schedule {
            val timeTaskEntity = input.timeTasks
            val timeTask = timeTaskEntity.map { it.mapToDomain() }

            return Schedule(
                date = input.dailySchedule.date,
                timeTask = timeTask,
                status = scheduleStatusChecker.fetchStatus(input.dailySchedule.date.mapToDate(), dateManager.fetchCurrentDate())
            )


        }

    }
}

fun ScheduleDetails.map(scheduleToDomainMapper: ScheduleToDomainMapper) = scheduleToDomainMapper.map(this)