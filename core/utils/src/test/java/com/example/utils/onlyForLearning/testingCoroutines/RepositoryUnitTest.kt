/**
 * @author Amit Kumar on 11/01/26
 */

package com.example.utils.onlyForLearning.testingCoroutines

import android.content.SharedPreferences
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.eq
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.spy
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class RepositoryUnitTest {

    lateinit var cocktailApi: CocktailApi
    lateinit var sharedPreferences: SharedPreferences
    lateinit var sharedPreferenceEditor: SharedPreferences.Editor
    lateinit var cocktailRepository: CocktailRepository

    @Before
    fun setUp(){
        cocktailApi = mock()
        sharedPreferences = mock()
        sharedPreferenceEditor = mock()
        whenever(sharedPreferences.edit()).thenReturn(sharedPreferenceEditor)
        cocktailRepository = CocktailRepository.Base(cocktailApi,sharedPreferences)
    }
    @Test
    fun `SaveScore should save score to sharedPreference`(){
        val score = 100
        cocktailRepository.saveHighScore(score)
        inOrder(sharedPreferenceEditor){
            verify(sharedPreferenceEditor).putInt(any(),eq(score))
            verify(sharedPreferenceEditor).apply()
        }
    }

    @Test
    fun `getScore should return score from shared preference`(){
        cocktailRepository.getHighScore()
        verify(sharedPreferences).getInt(any(),any())
    }

    @Test
    fun `SaveScore should not save score to sharedPreference if lower`(){
        val highestScore = 100
        val currentScore = 10
        val spyRepository = spy(cocktailRepository)
        doReturn(highestScore)
            .whenever(spyRepository)
            .getHighScore()
        spyRepository.saveHighScore(currentScore)
        verify(sharedPreferenceEditor, never()).putInt(any(),eq(currentScore))
    }
}