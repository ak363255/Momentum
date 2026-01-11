/**
 * @author Amit Kumar on 11/01/26
 */

package com.example.utils.onlyForLearning.testingCoroutines

class Game(
    val score : Score = Score(highestScore = 0),
    val question: List<Question>
) {
    private var currentIndex = -1
     val currentScore: Int
        get() = score.currentScore
     val highestScore: Int
        get() = score.highestScore

     fun incrementScore() {
        score.incrementScore()
    }

    fun nextQuestion(): Question? {
        if (currentIndex + 1 >= question.size) return null
        currentIndex++
        return question[currentIndex]
    }

    fun answer(question: Question, answer: String) {
        if (question.answer(answer)) {
            incrementScore()
        }
    }
}