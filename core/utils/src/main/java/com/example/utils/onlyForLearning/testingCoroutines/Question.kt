/**
 * @author Amit Kumar on 11/01/26
 */

package com.example.utils.onlyForLearning.testingCoroutines

class Question(
    val correctOption : String,
    val incorrectOption : String,
    val imageUrl : String? = null
) {
    var answered : String?= null
        private set

    fun answer(answer : String) : Boolean{
        answered = answer
        if(answered != correctOption && answered != incorrectOption)throw IllegalStateException()
        return answered == correctOption

    }

    fun getOptions(sort : (List<String>)-> List<String> = {it.shuffled()}): List<String>{
           val options = listOf(correctOption,incorrectOption)
           return sort(options)
    }

}