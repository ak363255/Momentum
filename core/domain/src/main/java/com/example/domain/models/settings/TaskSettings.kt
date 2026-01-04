/**
 * @author Amit Kumar on 04/01/26
 */

package com.example.domain.models.settings

import com.example.utils.functional.TimePeriod

data class TaskSettings(
    val viewToggleStatus: ViewToggleStatus = ViewToggleStatus.COMPACT,
    val calendarButtonBehavior: CalendarButtonBehavior = CalendarButtonBehavior.SET_CURRENT_DATE,
    val secureMode : Boolean = false,
    val taskAnalyticsRange : TimePeriod = TimePeriod.WEEK
)