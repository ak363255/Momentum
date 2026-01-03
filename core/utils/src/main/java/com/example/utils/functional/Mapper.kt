/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.utils.functional

interface Mapper<I,O> {
    fun map(input : I):O
}

interface ParametrizedMapper<I,O,P>{
    fun map(input : I, param : P):O
}