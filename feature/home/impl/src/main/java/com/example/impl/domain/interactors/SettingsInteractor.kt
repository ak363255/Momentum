/**
 * @author Amit Kumar on 04/01/26
 */

package com.example.impl.domain.interactors

import com.example.domain.models.settings.TaskSettings
import com.example.impl.domain.model.HomeFailures
import com.example.utils.functional.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal interface SettingsInteractor  {
    fun fetchTaskSettings() : Flow<Either<HomeFailures, TaskSettings>>
    fun updateTaskSettings(taskSettings: TaskSettings) : Either<HomeFailures, Unit>

    class Base @Inject constructor(): SettingsInteractor{
        override fun fetchTaskSettings(): Flow<Either<HomeFailures, TaskSettings>> {
            // fetch task setting from repository and then local storage -> yet to implement
            return flow { emit(Either.Right(TaskSettings())) }
        }

        override fun updateTaskSettings(taskSettings: TaskSettings): Either<HomeFailures, Unit> {
            // update task setting from repository and then local storage -> yet to implement
            return Either.Right(Unit)
        }

    }

}