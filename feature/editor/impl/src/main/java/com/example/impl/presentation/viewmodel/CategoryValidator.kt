/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.impl.presentation.viewmodel

import com.example.impl.presentation.models.categories.MainCategoryUi
import com.example.utils.validation.ValidateError
import com.example.utils.validation.ValidateResult
import com.example.utils.validation.Validator
import javax.inject.Inject

internal interface CategoryValidator : Validator<MainCategoryUi, CategoryValidateError>{

    class Base @Inject constructor(): CategoryValidator{
        override fun validate(data: MainCategoryUi): ValidateResult<CategoryValidateError> {
            return if(data.id == 0){
                ValidateResult(false, CategoryValidateError.EmptyCategoryError)
            }else{
                ValidateResult(true,null)
            }
        }

    }
}

internal sealed class CategoryValidateError : ValidateError{
    object EmptyCategoryError : CategoryValidateError()
}