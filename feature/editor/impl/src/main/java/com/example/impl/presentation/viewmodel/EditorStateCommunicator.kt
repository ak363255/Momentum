/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.impl.presentation.viewmodel

import com.example.impl.presentation.viewmodel.contract.EditorViewState
import com.example.utils.platform.communications.state.StateCommunicator

internal interface EditorStateCommunicator : StateCommunicator<EditorViewState> {
    class Base : EditorStateCommunicator, StateCommunicator.Abstract<EditorViewState>(initialState = EditorViewState())
}