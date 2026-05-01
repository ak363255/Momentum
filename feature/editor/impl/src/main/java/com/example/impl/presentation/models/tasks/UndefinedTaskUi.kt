package com.example.impl.presentation.models.tasks

import com.example.domain.models.schedule.TaskPriority
import com.example.impl.presentation.models.categories.MainCategoryUi
import com.example.impl.presentation.models.categories.SubCategoryUi
import com.example.impl.presentation.models.editmodel.EditModelUi
import com.example.impl.presentation.models.editmodel.EditParameters
import com.example.utils.functional.TimeRange
import java.util.Date

internal data class UndefinedTaskUi(
    val id: Long = 0L,
    val createdAt: Date? = null,
    val deadline: Date? = null,
    val mainCategory: MainCategoryUi,
    val subCategory: SubCategoryUi? = null,
    val priority: TaskPriority = TaskPriority.STANDARD,
    val note: String? = null
) {

    internal fun UndefinedTaskUi.convertToEditModel(
        scheduleDate: Date,
        timeRange: TimeRange,
    ) = EditModelUi(
        date = scheduleDate,
        timeRange = timeRange,
        createdAt = createdAt,
        mainCategory = mainCategory,
        subCategory = subCategory,
        parameters = EditParameters(
            priority = priority,
        ),
        undefinedTaskId = id,
        note = note,
    )

}


