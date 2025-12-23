/**
 * @author Amit Kumar on 23/12/25
 */

package com.example.momentum.di.modules

import com.example.data.coroutine.DispatcherHolderImpl
import com.example.domain.coroutine.DispatchersHolder
import com.example.utils.di.annotations.DefaultDispatcher
import com.example.utils.di.annotations.IoDispatcher
import com.example.utils.di.annotations.UiDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext


@Module
@InstallIn(SingletonComponent::class)
class CoroutineModule {

    @Provides
    @Singleton
    @UiDispatcher
    fun provideUiDispatcher(): CoroutineDispatcher = Dispatchers.Main


    @Provides
    @Singleton
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO


    @Provides
    @Singleton
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Singleton
    fun provideDispatchersHolder(dispatchersHolder: DispatcherHolderImpl): DispatchersHolder = dispatchersHolder
}