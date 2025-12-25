/**
 * @author Amit Kumar on 22/11/25
 */

package com.example.momentum.presentation.viewmodel

import android.util.Log
import com.example.domain.models.settings.Setting
import com.example.impl.mappers.mapToUi
import com.example.momentum.domain.common.MainFailures
import com.example.momentum.domain.interactors.SettingInteractor
import com.example.momentum.presentation.contract.MainAction
import com.example.momentum.presentation.contract.MainEffect
import com.example.momentum.presentation.contract.MainEvent
import com.example.momentum.presentation.contract.MainViewState
import com.example.utils.di.annotations.IoDispatcher
import com.example.utils.functional.Either
import com.example.utils.managers.CoroutineManager
import com.example.utils.platform.viemodel.BaseViewModel
import com.example.utils.platform.viemodel.work.WorkProcessor
import com.example.utils.platform.viemodel.work.WorkScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingWorkProcessor: SettingsWorkProcessor,
    private val mainStateCommunicator: MainStateCommunicator,
    private val mainEffectCommunicator: MainEffectCommunicator,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel<MainEvent, MainAction, MainEffect, MainViewState>(
    dispatcher = dispatcher,
    effectCommunicator = mainEffectCommunicator,
    stateCommunicator = mainStateCommunicator,

) {
    init {
        dispatchEvent(MainEvent.LoadSetting)
    }

    override fun reduce(
        action: MainAction,
        currentState: MainViewState
    ): MainViewState {
        return when(action){
            is MainAction.ChangeSettings -> currentState.copy(
                language = action.languageUiType,
                theme = action.themeUiType,
                color = action.colorsUiType
            )
        }
    }

    override fun WorkScope<MainViewState, MainAction, MainEffect>.handleEvent(
        event: MainEvent
    ) {
        when(event){
            MainEvent.LoadSetting -> {
                launchBackgroundWork(scope = scope,dispatcher = dispatcher){
                    settingWorkProcessor.doWork(SettingsWorksCommand.LoadSettings).collectAndHandleWork()
                }
            }
        }

    }

}