/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.impl.presentation.viewmodel

import com.example.impl.presentation.viewmodel.contract.EditorEffect
import com.example.utils.platform.communications.state.EffectCommunicator

internal interface EditorEffectCommunicator : EffectCommunicator<EditorEffect> {
     class Base : EditorEffectCommunicator, EffectCommunicator.Abstract<EditorEffect>()
}