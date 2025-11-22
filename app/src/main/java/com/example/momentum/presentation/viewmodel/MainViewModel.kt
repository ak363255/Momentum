/**
 * @author Amit Kumar on 22/11/25
 */

package com.example.momentum.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.momentum.presentation.contract.MainAction
import com.example.momentum.presentation.contract.MainEffect
import com.example.momentum.presentation.contract.MainEvent
import com.example.momentum.presentation.contract.MainViewState
import com.example.utils.functional.Either
import com.example.utils.managers.CoroutineManager
import com.example.utils.platform.viemodel.BaseViewModel
import kotlinx.coroutines.flow.flow

class MainViewModel(initialState : MainViewState,coroutineManager: CoroutineManager)
    : BaseViewModel<MainEvent, MainAction, MainEffect, MainViewState>(coroutineManager,initialState) {

        init {
            sendEvent(MainEvent.LoadSetting)
        }
    override suspend fun handleEvent(event: MainEvent) {
        Log.d(TAG,"handle Event called ->${event}")
       when(event){
           MainEvent.LoadSetting -> flow<Either<MainEffect, MainAction>> {
               emit(Either.Right(MainAction.OnSettingResult))
               emit(Either.Left(MainEffect.GoToMainPage))
           }.collectAndHandleFlow()
       }
    }

    override suspend fun reduce(
        action: MainAction,
        state: MainViewState
    ): MainViewState {
        return when(action){
            MainAction.OnSettingResult -> state.copy()
        }
    }

    class MainViewModelFactory : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(initialState = MainViewState(), coroutineManager = CoroutineManager.Base()) as T
        }
    }
}