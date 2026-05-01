/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.domain.models.template

import com.example.domain.models.categories.MainCategory
import com.example.domain.models.categories.SubCategory
import com.example.domain.models.schedule.TaskPriority
import java.util.Date

data class Template(
    val templateId : Int,
    val startTime : Date,
    val endTime  : Date,
    val category: MainCategory,
    val subCategory: SubCategory? = null,
    val priority: TaskPriority = TaskPriority.STANDARD,
    val isEnableNotification : Boolean = true,
    val isConsiderInStatistics : Boolean = true,
    val repeatEnabled : Boolean = false,
    val repeatTimes : List<RepeatTime> = emptyList()
)