/**
 * @author Amit Kumar on 11/01/26
 */

package com.example.utils.onlyForLearning.testingCoroutines

interface CocktailsGameFactory {

    fun build(callback: Callback)

    interface Callback {
        fun success(game: Game)
        fun onError()
    }

    class Base(val cocktailRepository: CocktailRepository) : CocktailsGameFactory {
        override fun build(callback: Callback) {
            cocktailRepository.getAlcoholic(
                object : RepositoryCallback<List<Cocktail>, String> {
                    override fun success(data: List<Cocktail>) {
                        val scope = Score(cocktailRepository.getHighScore())
                        val questions = buildQuestions(data)
                        callback.success(Game(question = questions, score = scope))
                    }
                    override fun onError(e: String) {
                          callback.onError()
                    }

                }
            )

        }

        private fun buildQuestions(cocktails : List<Cocktail>):List<Question> = cocktails.map { cocktail ->
            val otherCocktail = cocktails.shuffled().first { it != cocktail }
            val question = Question(cocktail.strDrink,otherCocktail.strDrink,cocktail.strDrinkThumb)
            question
        }
    }
}