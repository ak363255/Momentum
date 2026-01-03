/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.domain.common

import com.example.domain.models.schedule.DailScheduleStatus
import java.util.Date

interface ScheduleStatusChecker {
    fun fetchStatus(requiredDate: Date, currentDate: Date): DailScheduleStatus

    class Base : ScheduleStatusChecker {
        override fun fetchStatus(
            requiredDate: Date,
            currentDate: Date
        ): DailScheduleStatus {
            return if (requiredDate > currentDate) DailScheduleStatus.PLANNED
            else if (requiredDate < currentDate) DailScheduleStatus.REALIZED
            else DailScheduleStatus.ACCOMPLISHMENT
        }

    }
}