/**
 * @author Amit Kumar on 05/01/26
 */

package com.example.impl.presentation.viewmodel.contract

import com.example.utils.platform.communications.state.StateCommunicator
import javax.inject.Inject

internal interface HomeStateCommunicator  : StateCommunicator<HomeState>{
    class Base @Inject constructor() : HomeStateCommunicator, StateCommunicator.Abstract<HomeState>(initialState = HomeState())
}