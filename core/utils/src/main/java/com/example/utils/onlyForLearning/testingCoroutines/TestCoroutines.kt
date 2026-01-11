/**
 * @author Amit Kumar on 10/01/26
 */

package com.example.utils.onlyForLearning.testingCoroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest


fun main(){
   // testCoroutineScheduleAndStandardTestDispatcher()
    testRunTest()
}
//TestCoroutineScheduler and StandardTestDispatcher
fun testCoroutineScheduleAndStandardTestDispatcher(){
    val testCoroutineScheduler = TestCoroutineScheduler()
    val testDispatcher = StandardTestDispatcher(testCoroutineScheduler)
    CoroutineScope(testDispatcher).launch {
        println("Will Not Called Itself")
        delay(2000)
        println("will not wait for this delay")
    }
    testCoroutineScheduler.advanceUntilIdle()
}

fun testTestScope(){
    val scope = TestScope()
    scope.launch {
        println("will not print until we call advanceUntilIdle")
    }
    scope.testScheduler.advanceUntilIdle()
}

fun testRunTest(){
    runTest {
        /// it auto advances until idle
        println("will be printed immediately")
        delay(2000)
        println("will be printed")
    }
}

