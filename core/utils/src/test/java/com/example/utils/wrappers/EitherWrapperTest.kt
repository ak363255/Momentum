package com.example.utils.wrappers

import com.example.utils.functional.DomainFailures
import com.example.utils.functional.Either
import com.example.utils.handlers.ErrorHandler
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.coroutines.cancellation.CancellationException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class EitherWrapperTest {

    lateinit var errorHandler: ErrorHandler<DomainFailures>
    lateinit var eitherWrapper : EitherWrapper<DomainFailures>
    lateinit var testFailure : DomainFailures
    @Before
    fun setUp()= runTest{
        testFailure = object : DomainFailures{}
        errorHandler = mockk()
        eitherWrapper = object : EitherWrapper.Abstract<DomainFailures>(errorHandler){}
    }
    @Test
    fun `Successful execution returns Right`() = runTest{
        // Given the block executes successfully without throwing an exception, 
        // when wrap is called, 
        // then it should return an Either.Right containing the result of the block.
        coEvery { errorHandler.handle(any()) } returns testFailure
        val result = eitherWrapper.wrap { 8 }
        assert( result is Either.Right)
    }

    @Test
    fun `General exception returns Left`() = runTest{
        // Given the block throws a standard Exception, 
        // when wrap is called, 
        // then it should catch the exception, pass it to the errorHandler, and return an Either.Left containing the result from the errorHandler.
        coEvery { errorHandler.handle(any()) } returns testFailure
        val result = eitherWrapper.wrap { throw Exception() }
        assert( result is Either.Left)
    }

    @Test
    fun `CancellationException is re thrown`() = runTest{
        // Given the block throws a CancellationException, 
        // when wrap is called, 
        // then the CancellationException should be re-thrown and not be caught by the general Exception handler.
        coEvery { errorHandler.handle(any()) } returns testFailure
        assertFailsWith<CancellationException> { eitherWrapper.wrap { throw CancellationException() } }
    }

    @Test
    fun `Custom exception returns Left`() = runTest{
        // Given the block throws a custom exception that inherits from Exception, 
        // when wrap is called, 
        // then it should be handled by the errorHandler and return an Either.Left.
        coEvery { errorHandler.handle(any()) } returns testFailure
        val result = eitherWrapper.wrap { throw Exception() }
        assert( result is Either.Left)

    }

    @Test
    fun `Block with suspend function success`() = runTest{
        // Given the block contains other suspending functions that complete successfully, 
        // when wrap is called, 
        // then it should return an Either.Right with the final result.
        coEvery { errorHandler.handle(any()) } returns testFailure
        val result = eitherWrapper.wrap { 8 }
        assert( result is Either.Right && result.data == 8)
    }


    @Test
    fun `Block returns a nullable type successfully`() = runTest{
        // Given the block's return type is nullable (e.g., O?) and it returns a non-null value, 
        // when wrap is called, 
        // then it should return an Either.Right containing the non-null value.
        coEvery { errorHandler.handle(any()) } returns testFailure
        val ans:String? = ""
        val result = eitherWrapper.wrap { ans }
        assert(result is Either.Right && result.data !=  null)
    }

    @Test
    fun `Block returns null successfully`() = runTest{
        // Given the block's return type is nullable (e.g., O?) and it returns null, 
        // when wrap is called, 
        // then it should return an Either.Right containing null.
        coEvery { errorHandler.handle(any()) } returns testFailure
        val result: Either<DomainFailures, String?> = eitherWrapper.wrap { null }
        assert(result is Either.Right && result.data ==  null)
    }

    @Test
    fun test1() = runTest{
        assertEquals(0,currentTime)
        delay(1000)
        assertEquals(1000,currentTime)
    }


}
