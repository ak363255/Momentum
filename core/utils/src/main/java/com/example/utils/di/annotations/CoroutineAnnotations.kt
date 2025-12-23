/**
 * @author Amit Kumar on 23/12/25
 */

package com.example.utils.di.annotations

import javax.inject.Qualifier

@Qualifier
@Retention(value = AnnotationRetention.RUNTIME)
annotation class UiDispatcher


@Qualifier
@Retention(value = AnnotationRetention.RUNTIME)
annotation class DefaultDispatcher


@Qualifier
@Retention(value = AnnotationRetention.RUNTIME)
annotation class IoDispatcher