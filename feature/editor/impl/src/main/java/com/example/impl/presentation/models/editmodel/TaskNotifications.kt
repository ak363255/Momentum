/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.impl.presentation.models.editmodel

data class TaskNotifications(
    val fifteenMinutesBefore : Boolean = false,
    val oneHourBefore : Boolean = false,
    val threeHourBefore : Boolean = false,
    val oneDayBefore : Boolean = false,
    val oneWeekBefore : Boolean = false,
    val beforeEnd : Boolean = false
)