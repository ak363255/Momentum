/**
 * @author Amit Kumar on 21/12/25
 */

package com.example.utils.platform.services

import android.app.Application

abstract class BaseApplication : Application(){

    abstract val crashlyticsService : CrashlyticsService
    abstract val analyticsService : AnalyticsService
    abstract val appService : AppService
    abstract fun initDi()
    abstract fun initPlatformService()
    abstract fun initSetting()



    override fun onCreate() {
        super.onCreate()
        initDi()
        initPlatformService()
        initSetting()
    }


}