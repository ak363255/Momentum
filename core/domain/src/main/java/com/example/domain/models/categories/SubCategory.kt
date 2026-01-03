/**
 * @author Amit Kumar on 02/01/26
 */

package com.example.domain.models.categories

data class SubCategory(
    val id : Int = 0,
    val name : String?,
    val mainCategory: MainCategory,
    val description : String?
)