/**
 * @author Amit Kumar on 21/12/25
 */

package com.example.momentum.application

import com.example.utils.platform.services.AnalyticsService
import com.example.utils.platform.services.AppService
import com.example.utils.platform.services.BaseApplication
import com.example.utils.platform.services.CrashlyticsService
import javax.inject.Inject

abstract class MomentumBaseApplication : BaseApplication() {
    @Inject
    override lateinit var crashlyticsService: CrashlyticsService
    @Inject
    override lateinit var analyticsService: AnalyticsService
    @Inject
    override lateinit var  appService: AppService


    override fun initPlatformService() {
        //initialize crashlytics service
        crashlyticsService.initializeService()
        //initialize analytics service
        analyticsService.initializeService()
        //initialize app service
        appService.initializeService()
    }
}