/**
 * @author Amit Kumar on 05/01/26
 */

package com.example.impl.presentation.viewmodel.contract

import com.example.utils.platform.communications.state.EffectCommunicator
import javax.inject.Inject

internal interface HomeEffectCommunicator : EffectCommunicator<HomeEffect> {
    class Base @Inject constructor() : HomeEffectCommunicator, EffectCommunicator.Abstract<HomeEffect>()
}