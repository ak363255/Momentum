/**
 * @author Amit Kumar on 12/01/26
 */

package com.example.utils.onlyForLearning.testingCoroutines.flowTesting

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.time.Duration

class MessageServiceTest {

    @Test
    fun `should emit message from user`() = runTest {
        val source = flowOf(
            Message(id = "1"),
            Message(id = "2"),
            Message(id = "3"),
            Message(id = "4"),
            Message(id = "1"),
        )
        val messageService = MessageService(source, scope = backgroundScope)
        val result = messageService.observeMessage("1").take(2).toList()
        assertEquals(
            listOf(Message("1"), Message("1")), result
        )

    }

    @Test
    fun `should emit message from user with dealy`() = runTest {
        val source = flow {
            emit(Message(id = "1"))
            delay(1000)
            emit(Message(id = "2"))
            emit(Message(id = "3"))
            emit(Message(id = "4"))
            emit(Message(id = "1"))
        }
        val emitMessages = mutableListOf<Message>()
        val messageService = MessageService(source, scope = backgroundScope)
        messageService.observeMessage("1").onEach { emitMessages.add(it) }.launchIn(backgroundScope)
        /* assertEquals(
             listOf(Message("1"), Message("1")),emitMessages
         )*/
        delay(1001)
        assertEquals(
            listOf(Message("1"), Message("1")), emitMessages
        )
    }

    @Test
    fun `should emit message from user in given duration`() = runTest {
        val source = flow {
            emit(Message(id = "1"))
            delay(1000)
            emit(Message(id = "2"))
            emit(Message(id = "3"))
            emit(Message(id = "4"))
            emit(Message(id = "1"))
        }
        val messageService = MessageService(source, scope = backgroundScope)
        val result = messageService.observeMessage("1").toListDuring(1001)
        assertEquals(
            listOf(Message("1"), Message("1")), result
        )
    }
    private val infiniteFlow = flow {
        var i = 0
        while (true){
            emit(Message(id = "${i++}"))
            delay(1000)
        }
    }

    @Test
    fun `should start at most one connection`() = runTest{
        var counter = 0
        val source = infiniteFlow.onStart { counter++ }.onCompletion { counter-- }
        val messageService = MessageService(source, scope = backgroundScope)
        messageService.observeMessage("0").launchIn(backgroundScope)
        messageService.observeMessage("1").launchIn(backgroundScope)
        messageService.observeMessage("2").launchIn(backgroundScope)
        delay(1000)
        assertEquals(1,counter)
    }

    suspend fun <T> Flow<T>.toListDuring(timeInMilli: Long): List<T> = coroutineScope {
        val result = mutableListOf<T>()
        val job = launch {
            collect {
                result.add(it)
            }
        }
        delay(timeInMilli)
        job.cancel()
        return@coroutineScope result


    }
}