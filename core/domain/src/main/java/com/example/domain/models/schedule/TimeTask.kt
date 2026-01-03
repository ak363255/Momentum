/**
 * @author Amit Kumar on 02/01/26
 */

package com.example.domain.models.schedule

import com.example.domain.models.categories.MainCategory
import com.example.domain.models.categories.SubCategory
import com.example.utils.functional.TimeRange
import java.util.Date

data class TimeTask(
    val key : Long,
    val date : Date,
    val createdAt : Date,
    val mainCategory : MainCategory,
    val subCategory : SubCategory?,
    val isEnableNotification : Boolean,
    val notificationTasks : TimeTaskNotification,
    val isConsiderInStatistics : Boolean,
    val timeRange : TimeRange,
    val note : String?,
    val priority : TaskPriority,
    val isCompleted : Boolean,
)