/**
 * @author Amit Kumar on 11/01/26
 */

package com.example.utils.onlyForLearning.testingCoroutines

import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.mockito.kotlin.verifyNoInteractions
import kotlin.test.assertNotNull

class GameTest {


    @Test
    fun incrementScore() {
        val game = Game(question = emptyList())
        game.incrementScore()
        assert(game.currentScore == 1)
    }

    @Test
    fun WhenIncrementingScoreBelowHighScoreShouldNotIncrementHighScope() {
        val game = Game(score = Score(10), emptyList())
        game.incrementScore()
        assert(game.currentScore == 1)
        assert(game.highestScore != 11)
    }


    @Test
    fun `Game should have list of questin`() {
        val questionList = listOf(Question("CORRECT", "INCORRECT"))
        val game = Game(question = questionList)
        assertNotNull(game.question)
    }

    @Test
    fun `Game should have at least one  question`() {
        val questionList = listOf(Question("CORRECT", "INCORRECT"))
        val game = Game(question = questionList)
        assert(game.question.size > 0)
    }

    @Test
    fun `Game nextQuestion method should return next question`() {
        val questionList = listOf(
            Question("CORRECT0", "INCORRECT0"),
            Question("CORRECT1", "INCORRECT1"),
            Question("CORRECT2", "INCORRECT2"),
            Question("CORRECT3", "INCORRECT3"),
        )
        val game = Game(question =  questionList)
        val nextQuestion = game.nextQuestion()
        assert(nextQuestion != null)
        assert(nextQuestion == questionList[1])
    }

    @Test
    fun `Game nextQuestion method should return null if no more question`() {
        val questionList = listOf(
            Question("CORRECT0", "INCORRECT0")
        )
        val game = Game(question = questionList)
        val nextQuestion = game.nextQuestion()
        assert(nextQuestion == null)
    }

    @Test
    fun `When answering should delegate to question`() {
        val question: Question = mockk()
        every {
            question.answer(any())
        } returns true
        val game = Game(question = listOf(question))
        game.answer(question, "ANSWER")
        verify {
            question.answer(eq("ANSWER"))
        }
    }

    @Test
    fun `When answering incorrectly  should not increment score`() {
        val question: Question = mockk()
        every {
            question.answer(any())
        } returns false
        val game = Game(question = listOf(question))
        game.answer(question, "ANSWER")
        assert(game.currentScore == 0)
    }

    @Test
    fun `When answering correctly  should  increment score`() {
        val question: Question = mockk()
        every {
            question.answer(any())
        } returns true
        val game = Game(question =  listOf(question))
        game.answer(question, "ANSWER")
        assert(game.currentScore == 1)
    }

    @Test
    fun `When answering correctly  should  call score_increment`() {
        val question: Question = mockk()
        val score : Score = mockk()
        every {
            score.incrementScore()
        } just  Runs
        every {
            question.answer(any())
        } returns true
        val game = Game(score = score,question =  listOf(question))
        game.answer(question, "ANSWER")
        verify {
            score.incrementScore()
        }
    }


    @Test
    fun `When answering incorrectly  should  not call score_increment`() {
        val question: Question = mockk()
        val score : Score = mockk()
        every {
            score.incrementScore()
        } just  Runs
        every {
            question.answer(any())
        } returns false
        val game = Game(score = score,question =  listOf(question))
        game.answer(question, "INCORRECT")
        verify(exactly = 0) {
            score.incrementScore()
        }
    }
}