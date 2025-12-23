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
import com.example.utils.functional.Either
import com.example.utils.managers.CoroutineManager
import com.example.utils.platform.viemodel.BaseViewModel
import com.example.utils.platform.viemodel.work.WorkScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    coroutineManager: CoroutineManager,
    private val settingInteractor: SettingInteractor
) : BaseViewModel<MainEvent, MainAction, MainEffect, MainViewState>(
    coroutineManager,
    MainViewState()
) {
    init {
        sendEvent(MainEvent.LoadSetting)
    }


    override suspend fun WorkScope<MainViewState, MainAction, MainEffect>.handleEvent(
        event: MainEvent
    ) {
        Log.d(TAG, "handle Event called ->${event}")
        when (event) {
            MainEvent.LoadSetting -> {
                settingInteractor.fetchSettings()
                    .map<Either<MainFailures, Setting>, Either<MainEffect, MainAction>> {
                        when (it) {
                            is Either.Left<MainFailures> -> {
                                Either.Left(MainEffect.DoNothing)
                            }

                            is Either.Right<Setting> -> {
                                Either.Right<MainAction>(MainAction.ChangeSettings(
                                    languageUiType = it.data.themeSetting.languageType.mapToUi(),
                                    colorsUiType = it.data.themeSetting.colorsType.mapToUi(),
                                    themeUiType = it.data.themeSetting.themeType.mapToUi()
                                ))
                            }
                        }
                    }.collectAndHandleFlow()
            }
        }
    }

    override suspend fun reduce(
        action: MainAction,
        state: MainViewState
    ): MainViewState {
        return when (action) {
            is MainAction.ChangeSettings -> state.copy(
                language = action.languageUiType,
                color = action.colorsUiType,
                theme = action.themeUiType
            )
        }
    }
}