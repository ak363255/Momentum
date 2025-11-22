/**
 * @author Amit Kumar on 22/11/25
 */

package com.example.utils.functional

sealed class Either<out L,out R>{
    data class Left<T>(val data : T): Either<T, Nothing>()
    data class Right<T>(val data : T): Either<Nothing,T>()

    val isLeft = this is Left
    val isRight = this is Right
}

suspend fun <L,R,T> Either<L,R>.handleAndGet(onLeftAction : suspend (L)->T,onRightAction : suspend (R)->T):T{
    return when(this){
        is Either.Left<L> -> onLeftAction(this.data)
        is Either.Right<R> -> onRightAction(this.data)
    }
}