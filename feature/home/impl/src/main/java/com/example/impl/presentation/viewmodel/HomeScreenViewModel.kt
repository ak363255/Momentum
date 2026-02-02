/*
*
 * @author Amit Kumar on 05/01/26

 */



package com.example.impl.presentation.viewmodel

import com.example.api.navigation.EditorFeatureEntry
import com.example.impl.presentation.viewmodel.contract.HomeAction
import com.example.impl.presentation.viewmodel.contract.HomeEffect
import com.example.impl.presentation.viewmodel.contract.HomeEffectCommunicator
import com.example.impl.presentation.viewmodel.contract.HomeEvent
import com.example.impl.presentation.viewmodel.contract.HomeState
import com.example.impl.presentation.viewmodel.contract.HomeStateCommunicator
import com.example.impl.presentation.viewmodel.processor.ScheduleWorkCommand
import com.example.impl.presentation.viewmodel.processor.ScheduleWorkProcessor
import com.example.utils.di.annotations.IoDispatcher
import com.example.utils.platform.viemodel.BaseViewModel
import com.example.utils.platform.viemodel.work.WorkScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import java.sql.Date
import javax.inject.Inject

@HiltViewModel
internal class HomeScreenViewModel @Inject constructor(
    private val scheduleWorkProcessor: ScheduleWorkProcessor,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val homeEffectCommunicator: HomeEffectCommunicator,
    private val homeStatCommunicator: HomeStateCommunicator,
    private val editorFeatureEntry: EditorFeatureEntry
) : BaseViewModel<HomeEvent, HomeAction, HomeEffect, HomeState>(
    dispatcher = ioDispatcher,
    effectCommunicator = homeEffectCommunicator,
    stateCommunicator = homeStatCommunicator
) {

    init {
        dispatchEvent(HomeEvent.Init)
    }
    override fun reduce(
        action: HomeAction,
        currentState: HomeState
    ): HomeState {
        return when (action) {
            is HomeAction.SetUpEmptySchedule -> {
                currentState.copy(
                    currentDate = action.date,
                    dailyTaskStatus = action.scheduleStatus
                )
            }

            is HomeAction.SetUpSettings -> {
                currentState
            }

            is HomeAction.UpdateSchedule -> {
                currentState.copy(
                    currentDate = action.timeTaskUi.date,
                    timeTask = action.timeTaskUi.timeTasks,
                    dailyTaskStatus = action.timeTaskUi.status,
                )
            }
        }
    }

    override suspend fun WorkScope<HomeState, HomeAction, HomeEffect>.handleEvent(
        event: HomeEvent
    ) {
        when(event){
            is HomeEvent.ChangeTaskDoneUi -> {}
            HomeEvent.CreateSchedule -> {
                val currentDate = state().currentDate
                if(currentDate != null){
                    val command = ScheduleWorkCommand.CreateSchedule(currentDate)
                    scheduleWorkProcessor.doWork(command).collectAndHandleWork()
                }

            }
            HomeEvent.Init -> {}
            is HomeEvent.LoadSchedule -> {
                scheduleWorkProcessor.doWork(ScheduleWorkCommand.LoadScheduleByDate(event.date)).collectAndHandleWork()
            }
            is HomeEvent.PressAddTimeTaskButton -> {}
            is HomeEvent.PressEditTimeTaskButton -> {}
            HomeEvent.PressViewToggleButton -> {}
            HomeEvent.PressedOverviewButton -> {}
            is HomeEvent.TimeTaskShiftDown -> {}
            is HomeEvent.TimeTaskShiftUp -> {}
        }
    }

}
