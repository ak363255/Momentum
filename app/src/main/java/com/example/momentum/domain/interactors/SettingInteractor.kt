package com.example.momentum.domain.interactors

import com.example.domain.models.settings.Setting
import com.example.domain.repository.settings.ThemeSettingsRepository
import com.example.momentum.domain.common.MainFailures
import com.example.utils.functional.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

 interface  SettingInteractor{
    fun fetchSettings(): Flow<Either<MainFailures, Setting>>
    class Base @Inject constructor(
        private val themeSettingsRepository: ThemeSettingsRepository
    ): SettingInteractor{
        override fun fetchSettings(): Flow<Either<MainFailures, Setting>> {
            return themeSettingsRepository.fetchThemeSettingsFlow().map {
                Either.Right(Setting(themeSetting = it))
            }.catch {
                Either.Left(MainFailures.OtherError(Throwable()))
            }
        }

    }
}
