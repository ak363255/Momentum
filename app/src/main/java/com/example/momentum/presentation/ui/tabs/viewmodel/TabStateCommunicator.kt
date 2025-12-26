/**
 * @author Amit Kumar on 27/12/25
 */

package com.example.momentum.presentation.ui.tabs.viewmodel

import com.example.utils.platform.communications.state.StateCommunicator
import javax.inject.Inject

interface TabStateCommunicator : StateCommunicator<TabViewState> {
    class Base @Inject constructor() : TabStateCommunicator,
        StateCommunicator.Abstract<TabViewState>(
            TabViewState()
        )
}