/**
 * @author Amit Kumar on 01/05/26
 */

package com.example.utils.validation

interface Validator<D,E:ValidateError> {
    fun validate(data : D):ValidateResult<E>
}