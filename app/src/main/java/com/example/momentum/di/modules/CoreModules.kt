/**
 * @author Amit Kumar on 23/12/25
 */

package com.example.momentum.di.modules

import com.example.utils.managers.CoroutineManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoreModules {

    @Singleton
    @Binds
    fun bindCoroutineManager(coroutineManager: CoroutineManager.Base): CoroutineManager
}