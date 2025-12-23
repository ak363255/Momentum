/**
 * @author Amit Kumar on 21/12/25
 */

package com.example.momentum.application

import com.example.momentum.di.component.AppComponent
import dagger.hilt.android.HiltAndroidApp
import kotlin.getValue

@HiltAndroidApp
class MomentumApplication :  MomentumBaseApplication() {


    val appComponent by lazy {
        AppComponent.create(applicationContext)
    }
    override fun initDi() {
       // appComponent.inject(this)
    }

    override fun initSetting() {
    }
}