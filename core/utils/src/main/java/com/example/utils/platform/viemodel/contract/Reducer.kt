/**
 * @author Amit Kumar on 25/12/25
 */

package com.example.utils.platform.viemodel.contract

interface Reducer<S,A>{
    fun reduce(action : A,currentState : S):S
}