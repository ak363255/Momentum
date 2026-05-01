/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.impl.presentation.viewmodel.contract

import com.example.impl.domain.entity.EditModel
import com.example.impl.domain.entity.EditorFailures
import com.example.impl.presentation.models.categories.CategoriesUi
import com.example.impl.presentation.models.categories.MainCategoryUi
import com.example.impl.presentation.models.categories.SubCategoryUi
import com.example.impl.presentation.models.editmodel.EditModelUi
import com.example.impl.presentation.models.editmodel.EditParameters
import com.example.impl.presentation.models.tasks.UndefinedTaskUi
import com.example.impl.presentation.template.TemplateUi
import com.example.impl.presentation.viewmodel.CategoryValidateError
import com.example.impl.presentation.viewmodel.TimeRangeError
import com.example.utils.functional.TimeRange
import com.example.utils.platform.viemodel.contract.BaseAction
import com.example.utils.platform.viemodel.contract.BaseEffect
import com.example.utils.platform.viemodel.contract.BaseEvent
import com.example.utils.platform.viemodel.contract.BaseViewState


internal data class EditorViewState(
    val editUi : EditModelUi? = null,
    val categories : List<CategoriesUi> = emptyList(),
    val templates : List<TemplateUi>? = null,
    val undefinedTasks : List<UndefinedTaskUi>? = null,
    val timeRangeValid : TimeRangeError? = null,
    val categoryValid : CategoryValidateError? = null
): BaseViewState

internal sealed interface EditorAction : BaseAction{
    object Navigate : EditorAction
    data class SetUp(val editModel: EditModel, val categories : List<CategoriesUi>) : EditorAction
    data class UpdateUndefinedTasks(val tasks : List<UndefinedTaskUi>) : EditorAction
    data class UpdateCategories(val categories : List<CategoriesUi>) : EditorAction
    data class  UpdateTemplates(val templates : List<TemplateUi>) : EditorAction
    data class UpdateTemplateId(val templateId : Int?) : EditorAction
    data class UpdateEditModel(val editModel: EditModelUi?) : EditorAction
    data class SetValidError(val timeRanError : TimeRangeError?,val categoryValidateError: CategoryValidateError?)  : EditorAction
}

internal sealed class EditorEffect : BaseEffect{
    data class ShowError(val failures : EditorFailures): EditorEffect()
    data class ShowOverlayError(
        val currentTimeRange : TimeRange,
        val failures : EditorFailures.TimeOverlayError
    ): EditorEffect()
}


internal sealed interface EditorEvent : BaseEvent {
    object Init : EditorEvent
    object CreateTemplate : EditorEvent
    data class ApplyTemplate(val template: TemplateUi) : EditorEvent
    data class ApplyUndefinedTask(val task: UndefinedTaskUi) : EditorEvent
    data class ChangeTime(val timeRange: TimeRange) : EditorEvent
    data class ChangeCategories(val category: MainCategoryUi, val subCategory: SubCategoryUi) :
        EditorEvent

    data class ChangeNote(val note: String?) : EditorEvent
    data class ChangeParameters(val parameters: EditParameters) : EditorEvent
    data class AddSubCategory(val name: String) : EditorEvent
    data class NavigateToCategoryEditor(val category: MainCategoryUi) : EditorEvent
    data class NavigateToSubCategoryEditor(val category: SubCategoryUi) : EditorEvent
    data object PressDeleteButton : EditorEvent
    data object PressSaveButton : EditorEvent
    data object PressBackButton : EditorEvent
}