/**
 * @author Amit Kumar on 12/01/26
 */

package com.example.utils.onlyForLearning.testingCoroutines.flowTesting

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.shareIn

data class Message(val id : String)
class MessageService(
    messageSource: Flow<Message>,
    scope: CoroutineScope

) {
    private val source  = messageSource
        .shareIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed()
        )

    fun observeMessage(userId : String) = source.filter { it.id == userId }
}