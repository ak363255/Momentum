/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.impl.presentation.viewmodel.contract

import com.example.domain.models.schedule.DailScheduleStatus
import com.example.domain.models.settings.CalendarButtonBehavior
import com.example.domain.models.settings.TaskSettings
import com.example.domain.models.settings.ViewToggleStatus
import com.example.impl.domain.model.HomeFailures
import com.example.impl.presentation.model.schedule.ScheduleUi
import com.example.impl.presentation.model.schedule.TimeTaskUi
import com.example.utils.platform.viemodel.contract.BaseAction
import com.example.utils.platform.viemodel.contract.BaseEffect
import com.example.utils.platform.viemodel.contract.BaseEvent
import com.example.utils.platform.viemodel.contract.BaseViewState
import java.util.Date


internal sealed class HomeEvent : BaseEvent {
    data object Init : HomeEvent()
    data object PressedOverviewButton : HomeEvent()
    data object CreateSchedule : HomeEvent()
    data class LoadSchedule(val date: Date) : HomeEvent()
    data object PressViewToggleButton : HomeEvent()
    data class ChangeTaskDoneUi(val taskUi: TimeTaskUi) : HomeEvent()
    data class TimeTaskShiftUp(val task: TimeTaskUi) : HomeEvent()
    data class TimeTaskShiftDown(val task: TimeTaskUi) : HomeEvent()
    data class PressEditTimeTaskButton(val task: TimeTaskUi) : HomeEvent()
    data class PressAddTimeTaskButton(val startTime: Date, val endTime: Date) : HomeEvent()


}

internal sealed class HomeAction : BaseAction {
    data class SetUpSettings(val taskSettings: TaskSettings) : HomeAction()
    data class UpdateSchedule(val timeTaskUi: ScheduleUi) : HomeAction()
    data class SetUpEmptySchedule(val date: Date, val scheduleStatus: DailScheduleStatus?) :
        HomeAction()

}

sealed class HomeEffect : BaseEffect {
    data class ShowError(val homeFailures: HomeFailures) : HomeEffect()

}

internal data class HomeState(
    val currentDate: Date? = null,
    val timeTask: List<TimeTaskUi> = emptyList(),
    val dailyTaskStatus: DailScheduleStatus? = null,
    val taskViewStatus: ViewToggleStatus = ViewToggleStatus.COMPACT,
    val calendarButtonBehavior: CalendarButtonBehavior = CalendarButtonBehavior.SET_CURRENT_DATE

) : BaseViewState