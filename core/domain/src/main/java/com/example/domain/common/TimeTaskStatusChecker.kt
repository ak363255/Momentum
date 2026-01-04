/**
 * @author Amit Kumar on 04/01/26
 */

package com.example.domain.common

import com.example.domain.models.schedule.TimeTaskStatus
import com.example.utils.functional.TimeRange
import java.util.Date
import javax.inject.Inject

interface TimeTaskStatusChecker {
    fun fetchStatus(timeRange: TimeRange,currentDate  : Date): TimeTaskStatus

    class Base @Inject constructor(): TimeTaskStatusChecker{
        override fun fetchStatus(
            timeRange: TimeRange,
            currentDate: Date
        ): TimeTaskStatus {
            return if(timeRange.to.time < currentDate.time) TimeTaskStatus.COMPLETED
            else if(timeRange.from.time > currentDate.time) TimeTaskStatus.PLANNED
            else TimeTaskStatus.RUNNING
        }

    }

}