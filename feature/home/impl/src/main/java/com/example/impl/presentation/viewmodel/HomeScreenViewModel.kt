/*
*
 * @author Amit Kumar on 05/01/26

 */



package com.example.impl.presentation.viewmodel

import com.example.impl.presentation.viewmodel.contract.HomeAction
import com.example.impl.presentation.viewmodel.contract.HomeEffect
import com.example.impl.presentation.viewmodel.contract.HomeEffectCommunicator
import com.example.impl.presentation.viewmodel.contract.HomeEvent
import com.example.impl.presentation.viewmodel.contract.HomeState
import com.example.impl.presentation.viewmodel.contract.HomeStateCommunicator
import com.example.impl.presentation.viewmodel.processor.ScheduleWorkProcessor
import com.example.utils.di.annotations.IoDispatcher
import com.example.utils.platform.viemodel.BaseViewModel
import com.example.utils.platform.viemodel.work.WorkScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
internal class HomeScreenViewModel @Inject constructor(
    private val scheduleWorkProcessor: ScheduleWorkProcessor,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val homeEffectCommunicator: HomeEffectCommunicator,
    private val homeStatCommunicator: HomeStateCommunicator
) : BaseViewModel<HomeEvent, HomeAction, HomeEffect, HomeState>(
    dispatcher = ioDispatcher,
    effectCommunicator = homeEffectCommunicator,
    stateCommunicator = homeStatCommunicator
) {
    override fun reduce(
        action: HomeAction,
        currentState: HomeState
    ): HomeState {
        return when (action) {
            is HomeAction.SetUpEmptySchedule -> {
                currentState
            }

            is HomeAction.SetUpSettings -> {
                currentState
            }

            is HomeAction.UpdateSchedule -> {
                currentState
            }
        }
    }

    override suspend fun WorkScope<HomeState, HomeAction, HomeEffect>.handleEvent(
        event: HomeEvent
    ) {
    }

}
