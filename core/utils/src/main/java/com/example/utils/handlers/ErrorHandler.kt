/**
 * @author Amit Kumar on 03/01/26
 */

package com.example.utils.handlers

interface ErrorHandler<E> {
    suspend fun handle(error : Throwable):E
}