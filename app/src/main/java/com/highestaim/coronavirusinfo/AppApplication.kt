package com.highestaim.coronavirusinfo

import android.app.Application
import com.highestaim.coronavirusinfo.DI.Services
import com.highestaim.coronavirusinfo.DI.appRepositories
import com.highestaim.coronavirusinfo.DI.appViewModels
import com.highestaim.coronavirusinfo.appServices.PreferenceService
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppApplication)
            androidLogger(Level.DEBUG)
            modules(listOf(appRepositories, appViewModels, Services))
        }

        PreferenceService.get().injectContext(applicationContext)
    }
}