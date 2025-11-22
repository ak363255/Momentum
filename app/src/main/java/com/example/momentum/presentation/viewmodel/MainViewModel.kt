/**
 * @author Amit Kumar on 22/11/25
 */

package com.example.momentum.presentation.viewmodel

import com.example.momentum.presentation.contract.MainAction
import com.example.momentum.presentation.contract.MainEffect
import com.example.momentum.presentation.contract.MainEvent
import com.example.momentum.presentation.contract.MainViewState
import com.example.utils.managers.CoroutineManager
import com.example.utils.platform.viemodel.BaseViewModel

class MainViewModel(initialState : MainViewState,coroutineManager: CoroutineManager)
    : BaseViewModel<MainEvent, MainAction, MainEffect, MainViewState>(coroutineManager,initialState) {
    override suspend fun handleEvent(event: MainEvent) {

    }

    override suspend fun reduce(
        action: MainAction,
        state: MainViewState
    ): MainViewState {
        TODO("Not yet implemented")
    }
}