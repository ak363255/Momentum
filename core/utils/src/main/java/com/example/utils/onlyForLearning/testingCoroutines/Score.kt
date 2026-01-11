/**
 * @author Amit Kumar on 11/01/26
 */

package com.example.utils.onlyForLearning.testingCoroutines

class Score(highestScore : Int) {

    var highestScore : Int = highestScore
        private set

    var currentScore : Int = 0
        private set

    fun incrementScore(){
        currentScore++
        if(currentScore > highestScore)highestScore++
    }
}