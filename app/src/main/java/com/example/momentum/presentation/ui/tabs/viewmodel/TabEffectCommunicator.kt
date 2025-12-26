/**
 * @author Amit Kumar on 27/12/25
 */

package com.example.momentum.presentation.ui.tabs.viewmodel

import com.example.utils.platform.communications.state.EffectCommunicator
import javax.inject.Inject

interface TabEffectCommunicator  : EffectCommunicator<TabScreenEffect>{
    class Base @Inject constructor() : TabEffectCommunicator, EffectCommunicator.Abstract<TabScreenEffect>()
}