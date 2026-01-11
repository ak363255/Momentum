/**
 * @author Amit Kumar on 11/01/26
 */

package com.example.utils.onlyForLearning.testingCoroutines

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class CockGameFactoryUnitTest {

    private val cocktails = listOf(
        Cocktail("1", "Drink1", "image1"),
        Cocktail("2", "Drink2", "image2"),
        Cocktail("3", "Drink3", "image3"),
        Cocktail("4", "Drink4", "image4")
    )
    lateinit var cocktailRepository: CocktailRepository
    lateinit var cocktailGameFactory : CocktailsGameFactory

    @Before
    fun setUp(){
        cocktailRepository = mock()
        cocktailGameFactory = CocktailsGameFactory.Base(cocktailRepository)
    }

    @Test
    fun `build game should get cocktails from the cocktails from repo`(){
        cocktailGameFactory.build(mock())
        verify(cocktailRepository).getAlcoholic(any())
    }

    @Test
    fun `build game should call onSuccess`(){
        val callback = mock<CocktailsGameFactory.Callback>()
        doAnswer {
            val repoCallback : RepositoryCallback<List<Cocktail>, String> = it.getArgument(0)
            repoCallback.success(cocktails)
        }.whenever(cocktailRepository).getAlcoholic(any())
        cocktailGameFactory.build(callback)
        verify(callback).success(any())
    }

    @Test
    fun `build game should call onError`(){
        val callback = mock<CocktailsGameFactory.Callback>()
        doAnswer {
            val repoCallback : RepositoryCallback<List<Cocktail>, String> = it.getArgument(0)
            repoCallback.onError("Error")
        }.whenever(cocktailRepository).getAlcoholic(any())
        cocktailGameFactory.build(callback)
        verify(callback).onError()
    }

    @Test
    fun `build game should game with highest score`(){
        setUpRepositoryWithCocktails(cocktailRepository)
        val highScore = 100
        whenever(cocktailRepository.getHighScore()).thenReturn(highScore)
        cocktailGameFactory.build(object : CocktailsGameFactory.Callback{
            override fun success(game: Game) {
                assertEquals(highScore,game.highestScore)
            }
            override fun onError() {
            }

        })
    }

    private fun setUpRepositoryWithCocktails(cocktailRepository: CocktailRepository){
        doAnswer {
            val repoCallback : RepositoryCallback<List<Cocktail>, String> = it.getArgument(0)
            repoCallback.success(cocktails)
        }.whenever(cocktailRepository).getAlcoholic(any())
    }
}