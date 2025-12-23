/**
 * @author Amit Kumar on 21/12/25
 */

package com.example.momentum.di.component

import android.content.Context
import com.example.momentum.application.MomentumApplication
import com.example.momentum.di.modules.DatabaseModule
import com.example.momentum.di.modules.PlatformServiceModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        PlatformServiceModule::class
    ]
)
interface AppComponent {

    fun inject(application: MomentumApplication)
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun applicationContext(context : Context): Builder
        fun databaseModule(module: DatabaseModule): Builder
        fun platformServiceModule(module: PlatformServiceModule): Builder
        fun build(): AppComponent
    }
    companion object{
        fun create(context : Context): AppComponent{
            return DaggerAppComponent.builder()
                .applicationContext(context)
                .databaseModule(DatabaseModule())
                .platformServiceModule(PlatformServiceModule())
                .build()
        }
    }
}