package com.crgarridos.sample.savedstate.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class DemoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        registerActivityLifecycleCallbacks(LogActivityLifecycleCallbacks())
    }
}