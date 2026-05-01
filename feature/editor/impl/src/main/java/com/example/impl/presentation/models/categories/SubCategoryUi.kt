/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.impl.presentation.models.categories
import com.example.domain.models.categories.DefaultCategoryType
import com.example.ui.mappers.mapToString
import com.example.ui.theme.tokens.CoreStrings

data class SubCategoryUi(
    val id : Int,
    val customName : String?,
    val defaultType: DefaultCategoryType? = DefaultCategoryType.EMPTY
){
    fun fetchName(strings: CoreStrings) = when (customName != null && customName != "null") {
        true -> customName
        false -> defaultType?.mapToString(strings)
    }
}