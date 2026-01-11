/**
 * @author Amit Kumar on 11/01/26
 */

package com.example.utils.onlyForLearning.testingCoroutines

import org.junit.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class QuestionsUnitTest {


    @Test
    fun `Creating question should not have answer option`(){
        val question = Question("CORRECT","INCORRECT")
        assertNull(question.answered)
    }

    @Test
    fun `Answer  with incorrect option should return false`(){
        val question = Question("CORRECT","INCORRECT")
        val result = question.answer(question.incorrectOption)
        assert(!result)
    }

    @Test
    fun `Answer  with correct option should return true`(){
        val question = Question("CORRECT","INCORRECT")
        val result = question.answer(question.correctOption)
        assert(result)
    }

    @Test
    fun `Answer  with invalid option should throw exception`(){
        val question = Question("CORRECT","INCORRECT")
        assertFailsWith<IllegalStateException>{ question.answer("INVALID")}
    }


    @Test
    fun `Question contains getOptions methods that returns list of options`(){
        val question = Question("CORRECT","INCORRECT")
        val shuffledOption = question.getOptions()
        assert(shuffledOption.isNotEmpty())
    }
}