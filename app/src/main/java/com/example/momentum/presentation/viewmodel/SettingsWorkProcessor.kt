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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface SettingsWorkProcessor : FlowWorkProcessor<SettingsWorksCommand, MainAction, MainEffect> {

    class Base @Inject constructor(
        private val settingInteractor: SettingInteractor
    ) : SettingsWorkProcessor {
        override suspend fun doWork(command: SettingsWorksCommand): Flow<Either<MainAction, MainEffect>> =
            when (command) {
                SettingsWorksCommand.LoadSettings -> {
                    settingInteractor.fetchSettings()
                        .map<Either<MainFailures, Setting>, Either<MainAction, MainEffect>> { settingsEither ->
                            when (settingsEither) {
                                is Either.Left<MainFailures> -> Either.Right(MainEffect.DoNothing)
                                is Either.Right<Setting> -> Either.Left(
                                    MainAction.ChangeSettings(
                                        languageUiType = settingsEither.data.themeSetting.languageType.mapToUi(),
                                        colorsUiType = settingsEither.data.themeSetting.colorsType.mapToUi(),
                                        themeUiType = settingsEither.data.themeSetting.themeType.mapToUi()
                                    )
                                )
                            }

                        }
                }
            }


    }
}

sealed class SettingsWorksCommand : WorkCommand {
    data object LoadSettings : SettingsWorksCommand()
}