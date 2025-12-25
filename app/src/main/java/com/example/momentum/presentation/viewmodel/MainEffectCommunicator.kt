/**
 * @author Amit Kumar on 25/12/25
 */

package com.example.momentum.presentation.viewmodel

import com.example.momentum.presentation.contract.MainEffect
import com.example.utils.platform.communications.state.EffectCommunicator
import javax.inject.Inject

interface MainEffectCommunicator : EffectCommunicator<MainEffect>  {
    class Base @Inject constructor(): MainEffectCommunicator, EffectCommunicator.Abstract<MainEffect>()
}