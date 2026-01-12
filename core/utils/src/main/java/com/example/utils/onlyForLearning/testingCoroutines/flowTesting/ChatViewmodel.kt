/**
 * @author Amit Kumar on 12/01/26
 */

package com.example.utils.onlyForLearning.testingCoroutines.flowTesting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ChatViewmodel(
    val messageService: MessageService

) : ViewModel() {

    private  val _lastId : MutableStateFlow<String> = MutableStateFlow("-1")
    val lastId : StateFlow<String> get() = _lastId

    private val _messages : MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    val message : StateFlow<List<String>>  get() = _messages

    fun start(userId : String) {
        messageService.observeMessage(userId)
            .onEach {
                _lastId.value = it.id
                _messages.value = _messages.value + it.id
            }
            .launchIn(viewModelScope)
    }



}