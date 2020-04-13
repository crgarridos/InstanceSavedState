package com.crgarridos.sample.savedstate.application

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class LogActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    private fun log(activity: Activity, event: String) = Timber
        .tag("LifecycleEvent")
        .v("${activity::class.simpleName}.$event")


    private fun log(activity: Activity, event: String, bundle: Bundle?) = Timber
        .tag("LifecycleEvent")
        .v("${activity::class.simpleName}.$event state=$bundle")

    private val fragmentLifecycleCallbacks =
        LogFragmentLifecycleCallbacks()

    override fun onActivityCreated(
        activity: Activity,
        savedInstanceState: Bundle?
    ) = log(activity, "onCreate", savedInstanceState).also {
        with(activity as AppCompatActivity) {
            supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, true)
        }
    }

    override fun onActivityStarted(activity: Activity) = log(activity, "onStart")

    override fun onActivityResumed(activity: Activity) = log(activity, "onResume")

    override fun onActivityPaused(activity: Activity) = log(activity, "onPause")

    override fun onActivityStopped(activity: Activity) = log(activity, "onStop")

    override fun onActivitySaveInstanceState(
        activity: Activity,
        outState: Bundle
    ) = log(activity, "onSaveInstanceState", outState)

    override fun onActivityDestroyed(activity: Activity) = log(activity, "onDestroy")
}