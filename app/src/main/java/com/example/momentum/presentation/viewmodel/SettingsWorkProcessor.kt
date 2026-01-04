/**
 * @author Amit Kumar on 25/12/25
 */

package com.example.momentum.presentation.viewmodel

import android.util.Log
import com.example.domain.models.settings.Setting
import com.example.impl.mappers.mapToUi
import com.example.momentum.domain.common.MainFailures
import com.example.momentum.domain.interactors.SettingInteractor
import com.example.momentum.presentation.contract.MainAction
import com.example.momentum.presentation.contract.MainEffect
import com.example.utils.functional.Either
import com.example.utils.platform.viemodel.work.FlowWorkProcessor
import com.example.utils.platform.viemodel.work.WorkCommand
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

 interface SettingsWorkProcessor : FlowWorkProcessor<SettingsWorksCommand, MainAction, MainEffect> {

      class Base @Inject constructor(
        private val settingInteractor: SettingInteractor
    ) : SettingsWorkProcessor {
        @OptIn(ExperimentalCoroutinesApi::class)
        override suspend fun doWork(command: SettingsWorksCommand): Flow<Either<MainAction, MainEffect>> =
            when (command) {
                SettingsWorksCommand.LoadSettings -> {
                    settingInteractor.fetchSettings()
                        .flatMapLatest{ settingsEither ->
                            when (settingsEither) {
                                is Either.Left<MainFailures> -> flow { emit(Either.Right(MainEffect.DoNothing)) }
                                is Either.Right<Setting> ->
                                    flow {
                                        emit(
                                            Either.Left(
                                                MainAction.ChangeSettings(
                                                    languageUiType = settingsEither.data.themeSetting.languageType.mapToUi(),
                                                    colorsUiType = settingsEither.data.themeSetting.colorsType.mapToUi(),
                                                    themeUiType = settingsEither.data.themeSetting.themeType.mapToUi()
                                                )
                                            )
                                        )
                                        emit(Either.Right(MainEffect.GoToMainPage))
                                    }
                            }

                        }
                }
            }


    }
}

sealed class SettingsWorksCommand : WorkCommand {
    data object LoadSettings : SettingsWorksCommand()
}