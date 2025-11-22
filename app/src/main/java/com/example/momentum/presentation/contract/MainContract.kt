/**
 * @author Amit Kumar on 22/11/25
 */

package com.example.momentum.presentation.contract

import com.example.utils.platform.viemodel.contract.BaseAction
import com.example.utils.platform.viemodel.contract.BaseEffect
import com.example.utils.platform.viemodel.contract.BaseEvent
import com.example.utils.platform.viemodel.contract.BaseViewState

sealed class MainEvent : BaseEvent
sealed class MainAction : BaseAction
sealed class MainEffect : BaseEffect
class MainViewState : BaseViewState