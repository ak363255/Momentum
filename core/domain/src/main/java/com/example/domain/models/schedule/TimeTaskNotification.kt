/**
 * @author Amit Kumar on 02/01/26
 */

package com.example.domain.models.schedule


data class TimeTaskNotification(
    val fifteenMinBefore : Boolean,
    val oneHourBefore : Boolean,
    val threeHourBefore : Boolean,
    val oneDayBefore : Boolean,
    val oneWeekBefore : Boolean,
    val beforeEnd : Boolean
){

}

enum class TaskNotificationType(idAmount : Int){
    START(0),
    FIFTEEN_MIN_BEFORE(60),
    ONE_HOUR_BEFORE(10),
    THREE_HOUR_BEFORE(20),
    ONE_DAY_BEFORE(30),
    ONE_WEEK_BEFORE(50),
    AFTER_TASK_BEFORE_END(40),
}