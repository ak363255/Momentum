/**
 * @author Amit Kumar on 12/01/26
 */

package com.example.utils.onlyForLearning.testingCoroutines.flowTesting

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test
import kotlin.test.assertEquals

class ChatViewmodelTest  {


    val standardTestDispatcher = UnconfinedTestDispatcher()

    @Test
    fun `should expose messages from user`() = runTest(standardTestDispatcher){
        Dispatchers.setMain(standardTestDispatcher)
        val source = MutableSharedFlow<Message>()
        val fakeMessageSource = MessageService(source, scope = backgroundScope)
        val chatViewmodel = ChatViewmodel(fakeMessageSource)
        chatViewmodel.start("1")
        assertEquals("-1",chatViewmodel.lastId.value)
        assertEquals(emptyList(),chatViewmodel.message.value)
        source.emit(Message(id = "1"))
        assertEquals("1",chatViewmodel.lastId.value)
        source.emit(Message("1"))
        assertEquals("1",chatViewmodel.lastId.value)

    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
}