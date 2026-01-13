/**
 * @author Amit Kumar on 11/01/26
 */

package com.example.utils.onlyForLearning.testingCoroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CocktailGameViewModel(
    val cocktailRepository: CocktailRepository,
    val cocktailsGameFactory: CocktailsGameFactory
) : ViewModel() {

    private var game : Game? = null
    private val loadingLiveData = MutableLiveData<Boolean>()
    private val errorLiveData = MutableLiveData<Boolean>()
    private val questionLiveData = MutableLiveData<Question>()
    private val scoreLiveData = MutableLiveData<Score>()
    fun getLoading(): LiveData<Boolean> = loadingLiveData
    fun getError(): LiveData<Boolean> = errorLiveData
    fun getQuestion(): LiveData<Question> = questionLiveData
    fun getScore(): LiveData<Score> = scoreLiveData


    fun initGame(){
        loadingLiveData.value = true
        errorLiveData.value = false
        cocktailsGameFactory.build(object : CocktailsGameFactory.Callback{
            override fun success(game: Game) {
                loadingLiveData.value = false
                scoreLiveData.value = game.score
                this@CocktailGameViewModel.game = game
                nextQuestion()
            }

            override fun onError() {
                loadingLiveData.value = false
                errorLiveData.value = true
            }

        })
    }

    private var inCorrectCount = 0
    fun answer(question: Question,ans: String){
        val result = game?.answer(question,ans)?:false
        if(!result){
            inCorrectCount++
            if(inCorrectCount>=3){
                finishGame()
            }
        }else{
            inCorrectCount = 0
        }
    }

    fun finishGame(){

    }
    fun nextQuestion(){
        questionLiveData.value = game?.nextQuestion()
    }
}