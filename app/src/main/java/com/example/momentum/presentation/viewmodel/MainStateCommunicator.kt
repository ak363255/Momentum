/**
 * @author Amit Kumar on 25/12/25
 */

package com.example.momentum.presentation.viewmodel

import com.example.momentum.presentation.contract.MainViewState
import com.example.utils.platform.communications.Communicator
import com.example.utils.platform.communications.state.StateCommunicator
import javax.inject.Inject

interface MainStateCommunicator : StateCommunicator<MainViewState> {
    class Base @Inject constructor(): MainStateCommunicator, StateCommunicator.Abstract<MainViewState>(MainViewState())
}