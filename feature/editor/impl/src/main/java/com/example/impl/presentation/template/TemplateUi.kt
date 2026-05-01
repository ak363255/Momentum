/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.impl.presentation.template
import com.example.domain.models.schedule.TaskPriority
import com.example.domain.models.template.RepeatTime
import com.example.impl.presentation.models.categories.MainCategoryUi
import com.example.impl.presentation.models.categories.SubCategoryUi
import java.util.Date

internal data class TemplateUi(
    val templateId: Int,
    val startTime: Date,
    val endTime: Date,
    val category: MainCategoryUi,
    val subCategory: SubCategoryUi? = null,
    val priority: TaskPriority = TaskPriority.STANDARD,
    val isEnableNotification: Boolean = true,
    val isConsiderInStatistics: Boolean = true,
    val repeatEnabled: Boolean = true,
    val repeatTimes: List<RepeatTime> = emptyList(),
)
