/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.impl.presentation.model.schedule

internal data class TimeTaskNotificationUi(
    val fifteenMinutesBeforeNotify: Boolean = false,
    val oneHourBeforeNotify: Boolean = false,
    val threeHourBeforeNotify: Boolean = false,
    val oneDayBeforeNotify: Boolean = false,
    val oneWeekBeforeNotify: Boolean = false,
    val beforeEnd: Boolean = false
)