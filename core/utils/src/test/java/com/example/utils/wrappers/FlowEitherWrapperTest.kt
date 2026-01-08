package com.example.utils.wrappers

import com.example.utils.functional.DomainFailures
import com.example.utils.functional.Either
import com.example.utils.handlers.ErrorHandler
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith


class FlowEitherWrapperTest {

    lateinit var testFailure : DomainFailures
    lateinit var errorHandler: ErrorHandler<DomainFailures>
    lateinit var flowEitherWrapper : FlowEitherWrapper<DomainFailures>

    @Before
    fun setUp(){
        testFailure = object : DomainFailures{}
        errorHandler = mockk()
        coEvery { errorHandler.handle(any()) }coAnswers {testFailure}
        flowEitherWrapper = object : FlowEitherWrapper.Abstract<DomainFailures>(errorHandler){}
    }
    @Test
    fun `Successful flow emission wraps in Either Right`() = runTest{
        // Given a block that returns a Flow which successfully emits items,
        // When wrapFlow is called with that block,
        // Then it should return a Flow that emits each of those items wrapped in an Either.Right.
        val flow = flowOf(1)
        val result = flowEitherWrapper.wrapFlow { flow }
        assert(result.first() is Either.Right)
    }

    @Test
    fun `Flow completing without emission`() = runTest{
        // Given a block that returns an empty Flow (e.g., emptyFlow()),
        // When wrapFlow is called,
        // Then the resulting Flow should complete without emitting any items.
        val flow = flowOf<Int>()
        val result = flowEitherWrapper.wrapFlow { flow }
        assert(result.firstOrNull()==null)
    }

    @Test
    fun `Flow throwing a non CancellationException`() = runTest{
        // Given a block that returns a Flow which throws an exception other than CancellationException,
        // When wrapFlow is called,
        // Then it should catch the exception, pass it to the errorHandler, and emit an Either.Left containing the mapped failure.
      //  assertFailsWith<Exception> { flowEitherWrapper.wrapFlow<Either<DomainFailures, Int>> { throw kotlin.Exception() } }
        val f = flow <Int>{ throw Exception() }
         val result = flowEitherWrapper.wrapFlow { f }
         assert(result.first() is Either.Left<*>)

    }

    @Test
    fun `Flow throwing a CancellationException is re thrown`() = runTest {
        // Given a block that returns a Flow which throws a CancellationException,
        // When wrapFlow is called,
        // Then the CancellationException should be re-thrown and not caught to be wrapped in an Either.Left, allowing the coroutine to be cancelled.
        val f = flow<Int> { throw CancellationException() }
        val result = flowEitherWrapper.wrapFlow { f }
        assertFailsWith<CancellationException> { result.first() }

    }


}
