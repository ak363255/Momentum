/**
 * @author Amit Kumar on 11/01/26
 */

package com.example.utils.onlyForLearning.testingCoroutines

data class CocktailContainer(val cocktails: List<Cocktail>)

data class Cocktail(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String
)

interface CocktailApi {
    fun getAlcoholic(): CocktailContainer
    fun getByName(): CocktailContainer
    fun getById(): CocktailContainer
}