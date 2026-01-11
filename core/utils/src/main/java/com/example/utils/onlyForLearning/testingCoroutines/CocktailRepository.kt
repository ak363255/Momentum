/**
 * @author Amit Kumar on 11/01/26
 */

package com.example.utils.onlyForLearning.testingCoroutines

import android.content.SharedPreferences

interface CocktailRepository {

    fun saveHighScore(score: Int)
    fun getHighScore(): Int

    fun getAlcoholic(callback : RepositoryCallback<List<Cocktail>, String>)

    class Base(
        val cocktailApi: CocktailApi,
        val sharedPreferences: SharedPreferences
    ) : CocktailRepository {
        val HIGH_SCORE_KEY = "HIGH_SCORE_KEY"
        override fun saveHighScore(score: Int) {
              if(getHighScore() < score){
                  val editor = sharedPreferences.edit()
                  editor.putInt(HIGH_SCORE_KEY, score)
                  editor.apply()
              }
        }

        override fun getHighScore(): Int {
            return sharedPreferences.getInt(HIGH_SCORE_KEY, 0)
        }

        override fun getAlcoholic(callback: RepositoryCallback<List<Cocktail>, String>) {

        }

    }
}

interface RepositoryCallback<T,E>{
    fun success(data : T)
    fun onError(e : E)
}