/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.domain.models.template

import com.example.utils.extensions.fetchDayNumberByMax
import com.example.utils.extensions.fetchMonth
import com.example.utils.extensions.fetchWeekDay
import com.example.utils.extensions.fetchWeekNumber
import com.example.utils.functional.WeekDay
import java.util.Date

sealed class RepeatTime {
    abstract val type: RepeatTimeType
    abstract val key: Int

    data class WeekDays(val day: WeekDay) : RepeatTime() {
        override val type: RepeatTimeType
            get() = RepeatTimeType.WEEK_DAY
        override val key: Int
            get() = day.number
    }

    data class WeekDayInMonth(val day: WeekDay, val weekNumber: Int) : RepeatTime() {
        override val type: RepeatTimeType
            get() = RepeatTimeType.WEEK_DAY_IN_MONTH
        override val key: Int
            get() = day.number + weekNumber
    }
    data class MonthDay(val dayNumber: Int) : RepeatTime() {
        override val type: RepeatTimeType
            get() = RepeatTimeType.MONTH
        override val key: Int
            get() = dayNumber
    }

    data class Year(val month: com.example.utils.functional.Month, val dayNumber: Int) : RepeatTime() {
        override val type: RepeatTimeType
            get() = RepeatTimeType.YEAR
        override val key: Int
            get() = month.number + dayNumber
    }

    fun checkDayIsRepeat(date : Date): Boolean = when(this){
        is MonthDay -> date.fetchDayNumberByMax(dayNumber) == dayNumber
        is WeekDayInMonth -> date.fetchWeekDay() == day && date.fetchWeekNumber() == weekNumber
        is WeekDays -> date.fetchWeekDay() == day
        is Year -> date.fetchDayNumberByMax(dayNumber) == dayNumber && date.fetchMonth() == month
    }

}