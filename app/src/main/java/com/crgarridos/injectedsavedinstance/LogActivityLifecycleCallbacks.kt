package com.crgarridos.injectedsavedinstance

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class LogActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    private fun log(activity: Activity, event: String, bundle: Bundle? = null) = Timber
        .tag("LifecycleEvent")
        .v("${activity::class.simpleName}.$event${bundle?.let { " $it" }.orEmpty()}")

    private val fragmentLifecycleCallbacks = LogFragmentLifecycleCallbacks()

    override fun onActivityCreated(
        activity: Activity,
        savedInstanceState: Bundle?
    ) = log(activity, "onActivityCreated").also {
        with(activity as AppCompatActivity) {
            supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, true)
        }
    }

    override fun onActivityStarted(activity: Activity) = log(activity, "onActivityStarted")

    override fun onActivityResumed(activity: Activity) = log(activity, "onActivityResumed")

    override fun onActivityPaused(activity: Activity) = log(activity, "onActivityPaused")

    override fun onActivityStopped(activity: Activity) = log(activity, "onActivityStopped")

    override fun onActivitySaveInstanceState(
        activity: Activity,
        outState: Bundle
    ) = log(activity, "onActivitySaveInstanceState", outState)

    override fun onActivityDestroyed(activity: Activity) = log(activity, "onActivityDestroyed")
}