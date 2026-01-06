/**
 * @author Amit Kumar on 04/01/26
 */

package com.example.impl.presentation.mappers.schedule

import com.example.domain.models.schedule.Schedule
import com.example.impl.presentation.model.schedule.ScheduleUi
import com.example.utils.extensions.mapToDate
import com.example.utils.functional.Mapper
import com.example.utils.managers.DateManager
import javax.inject.Inject

internal interface ScheduleDomainToUiMapper : Mapper<Schedule, ScheduleUi>{
    class Base @Inject constructor(
        private val timeTaskMapper : TimeTaskDomainToUiMapper,
        private val dateManager: DateManager
    ): ScheduleDomainToUiMapper{
        override fun map(input: Schedule): ScheduleUi {
            return ScheduleUi(
                date = input.date.mapToDate(),
                timeTasks = input.timeTask.map { timeTaskMapper.map(
                    input = it,
                    param = false
                ) },
                status = input.status,
                progress = when(input.timeTask.isEmpty()){
                    true -> 0f
                    false -> input.timeTask.count { dateManager.fetchCurrentDate().time > it.timeRange.to.time && it.isCompleted }/input.timeTask.size.toFloat()
                },
            )
        }

    }
}