/**
 * @author Amit Kumar on 11/01/26
 */

package com.example.utils.onlyForLearning.testingCoroutines

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.spy
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


class CocktailGameViewmodelTest {

    lateinit var cocktailRepository: CocktailRepository
    lateinit var cocktailsGameFactory: CocktailsGameFactory
    lateinit var game: Game
    lateinit var cocktailGameViewModel: CocktailGameViewModel
    lateinit var loadingObserver: Observer<Boolean>
    lateinit var errorObserver: Observer<Boolean>
    lateinit var scoreObserver: Observer<Score>
    lateinit var questionObserver: Observer<Question>

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        cocktailRepository = mock()
        cocktailsGameFactory = mock()
        cocktailGameViewModel = CocktailGameViewModel(cocktailRepository, cocktailsGameFactory)
        game = mock()
        loadingObserver = mock()
        errorObserver = mock()
        scoreObserver = mock()
        questionObserver = mock()
        cocktailGameViewModel.getLoading().observeForever(loadingObserver)
        cocktailGameViewModel.getError().observeForever(errorObserver)
        cocktailGameViewModel.getScore().observeForever(scoreObserver)
        cocktailGameViewModel.getQuestion().observeForever(questionObserver)
    }


    @Test
    fun `initGame should build game`(){
        val game = Game(question = emptyList())
        setUpFactoryWithSuccess(game)
        cocktailGameViewModel.initGame()
        verify(cocktailsGameFactory).build(any())
    }

    @Test
    fun `init should show loading`(){
        val game = Game(question = emptyList())
        setUpFactoryWithSuccess(game)
        cocktailGameViewModel.initGame()
        verify(loadingObserver).onChanged(eq(true))
    }
    @Test
    fun `init should hide error`(){
        val game = Game(question = emptyList())
        setUpFactoryWithSuccess(game)
        cocktailGameViewModel.initGame()
        verify(errorObserver).onChanged(eq(false))

    }

    @Test
    fun `init should show error when factory return error`(){
        setFactoryWithError()
        cocktailGameViewModel.initGame()
        verify(errorObserver).onChanged(eq(true))
    }
    @Test
    fun `init should hide loader when factory return error`(){
        setFactoryWithError()
        cocktailGameViewModel.initGame()
        verify(loadingObserver).onChanged(eq(false))
    }

    @Test
    fun `should hide error when factory return success`(){
        val game = Game(question = emptyList())
        setUpFactoryWithSuccess(game)
        cocktailGameViewModel.initGame()
        verify(errorObserver).onChanged(eq(false))
    }


    @Test
    fun `should hide loader when factory return success`(){
        val game = Game(question = emptyList())
        setUpFactoryWithSuccess(game)
        cocktailGameViewModel.initGame()
        verify(loadingObserver).onChanged(eq(false))
    }

    @Test
    fun `init should show score`(){
        val score = mock<Score>()
        setUpFactoryWithSuccess(game= game)
        whenever(game.score).thenReturn(score)
        cocktailGameViewModel.initGame()
        verify(scoreObserver).onChanged(eq(score))
    }

    @Test
    fun `should show first question when factory return success`(){
        val question = mock<Question>()
        setUpFactoryWithSuccess(game)
        whenever(game.nextQuestion()).thenReturn(question)
        cocktailGameViewModel.initGame()
        verify(questionObserver).onChanged(eq(question))
    }
    @Test
    fun `next question shoud next question`(){
        val question1 = mock<Question>()
        val question2 = mock<Question>()
        setUpFactoryWithSuccess(game)
        whenever(game.nextQuestion()).thenReturn(question1).thenReturn(question2)
        cocktailGameViewModel.initGame()
        cocktailGameViewModel.nextQuestion()
        verify(questionObserver).onChanged(eq(question2))
    }


    @Test
    fun `When answering three times incorrectly should call finish game`() = runTest{
        whenever(game.answer(any(),any())).thenReturn(false)
        val spyViewmodel = spy(cocktailGameViewModel)
         setUpFactoryWithSuccess(game)
        spyViewmodel.initGame()
        spyViewmodel.answer(mock(),"INCORRECT")
        spyViewmodel.nextQuestion()
        spyViewmodel.answer(mock(),"INCORRECT")
        spyViewmodel.nextQuestion()
        spyViewmodel.answer(mock(),"INCORRECT")
        verify(spyViewmodel).finishGame()
    }


    private fun setUpFactoryWithSuccess(game: Game) {
        doAnswer {
            val callback: CocktailsGameFactory.Callback = it.getArgument(0)
            callback.success(game)
        }.whenever(cocktailsGameFactory).build(any())
    }

    private fun setFactoryWithError() {
        doAnswer {
            val callback: CocktailsGameFactory.Callback = it.getArgument(0)
            callback.onError()
        }.whenever(cocktailsGameFactory).build(any())
    }


}