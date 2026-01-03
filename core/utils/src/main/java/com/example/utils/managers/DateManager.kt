/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.utils.managers
import com.example.utils.extensions.setEndDay
import com.example.utils.extensions.setStartDay
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

interface DateManager {

    fun fetchCurrentDate(): Date
    fun fetchBeginningCurrentDay () : Date
    fun fetchEndCurrentDay(): Date
    fun calculateLeftTime(endTime : Date): Long
    fun calculateProgress(start:Date,end : Date): Float
    fun setCurrentHMS(date : Date): Date

    class Base @Inject constructor(): DateManager{
        override fun fetchCurrentDate(): Date {
            return Calendar.getInstance().time
        }

        override fun fetchBeginningCurrentDay(): Date {
            return Calendar.getInstance().setStartDay().time
        }

        override fun fetchEndCurrentDay(): Date {
           return Calendar.getInstance().setEndDay().time
        }

        override fun calculateLeftTime(endTime: Date): Long {
            return endTime.time - fetchCurrentDate().time
        }

        override fun calculateProgress(start: Date, end: Date): Float {
            val currentTime = fetchCurrentDate().time
            val startTime = start.time
            val endTime = end.time
            val pastTime = currentTime - startTime
            val duration = endTime - startTime
            return pastTime.toFloat() / duration.toFloat()
        }

        override fun setCurrentHMS(date: Date): Date {
             val currentCalendar = Calendar.getInstance()
            val targetCalendar = Calendar.getInstance().apply {
                time = date
                set(Calendar.HOUR_OF_DAY, currentCalendar.get(Calendar.HOUR_OF_DAY))
                set(Calendar.MINUTE, currentCalendar.get(Calendar.MINUTE))
                set(Calendar.SECOND, currentCalendar.get(Calendar.SECOND))
                set(Calendar.MILLISECOND, currentCalendar.get(Calendar.MILLISECOND))
            }
            return targetCalendar.time
        }

    }
}