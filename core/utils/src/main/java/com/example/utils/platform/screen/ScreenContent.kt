/**
 * @author Amit Kumar on 25/12/25
 */

package com.example.utils.platform.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.utils.platform.viemodel.contract.BaseAction
import com.example.utils.platform.viemodel.contract.BaseEffect
import com.example.utils.platform.viemodel.contract.BaseEvent
import com.example.utils.platform.viemodel.contract.BaseViewState
import com.example.utils.platform.viemodel.contract.ContractProvider

@Composable
fun <S: BaseViewState,E: BaseEvent,A: BaseAction,F: BaseEffect,CA: ContractProvider<S,E,A,F>>ScreenContent(
    contractProvider: CA,
    content : @Composable ScreenScope<S,E,A,F>.(S)-> Unit
) {
    val screenScope = remember<ScreenScope<S,E,A,F>>{
        ScreenScope.Base<S,E,A,F>(contractProvider)
    }
    screenScope.content(screenScope.fetchState())

}