package com.example.impl.presentation.models.editmodel

import com.example.domain.models.categories.SubCategory
import com.example.domain.models.template.RepeatTime
import com.example.impl.presentation.models.categories.MainCategoryUi
import com.example.impl.presentation.models.categories.SubCategoryUi
import com.example.utils.extensions.duration
import com.example.utils.functional.TimeRange
import java.util.Date

internal data class EditModelUi(
    val key: Long = 0L,
    val date: Date,
    val timeRange: TimeRange,
    val createdAt: Date? = null,
    val duration: Long = duration(timeRange.from, timeRange.to),
    val mainCategory: MainCategoryUi = MainCategoryUi(),
    val subCategory: SubCategoryUi? = null,
    val isCompleted: Boolean = true,
    val parameters: EditParameters = EditParameters(),
    val repeatEnabled: Boolean = false,
    val templateId: Int? = null,
    val undefinedTaskId: Long? = null,
    val repeatTimes: List<RepeatTime> = emptyList(),
    val note: String? = null
)
