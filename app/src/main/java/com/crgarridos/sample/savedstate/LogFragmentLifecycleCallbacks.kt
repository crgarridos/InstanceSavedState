package com.crgarridos.sample.savedstate

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import timber.log.Timber

class LogFragmentLifecycleCallbacks : FragmentManager.FragmentLifecycleCallbacks() {

    private fun log(fragment: Fragment, event: String) = Timber
        .tag("LifecycleEvent")
        .v("${fragment::class.simpleName}.$event")

    private fun log(fragment: Fragment, event: String, bundle: Bundle?) = Timber
        .tag("LifecycleEvent")
        .v("${fragment::class.simpleName}.$event state=$bundle")

    override fun onFragmentPreAttached(
        manager: FragmentManager,
        fragment: Fragment,
        context: Context
    ) = log(fragment, "onFragmentPreAttached")

    override fun onFragmentAttached(
        manager: FragmentManager,
        fragment: Fragment,
        context: Context
    ) = log(fragment, "onFragmentAttached")

    override fun onFragmentCreated(
        manager: FragmentManager,
        fragment: Fragment,
        savedInstanceState: Bundle?
    ) = log(fragment, "onFragmentCreated", savedInstanceState)

    override fun onFragmentActivityCreated(
        manager: FragmentManager,
        fragment: Fragment,
        savedInstanceState: Bundle?
    ) = log(fragment, "onFragmentActivityCreated", savedInstanceState)

    override fun onFragmentViewCreated(
        manager: FragmentManager,
        fragment: Fragment,
        v: View,
        savedInstanceState: Bundle?
    ) = log(fragment, "onFragmentViewCreated", savedInstanceState)

    override fun onFragmentStarted(
        manager: FragmentManager,
        fragment: Fragment
    ) = log(fragment, "onFragmentStarted")

    override fun onFragmentResumed(
        manager: FragmentManager,
        fragment: Fragment
    ) = log(fragment, "onFragmentResumed")

    override fun onFragmentPaused(
        manager: FragmentManager,
        fragment: Fragment
    ) = log(fragment, "onFragmentPaused")

    override fun onFragmentStopped(
        manager: FragmentManager,
        fragment: Fragment
    ) = log(fragment, "onFragmentStopped")

    override fun onFragmentSaveInstanceState(
        manager: FragmentManager,
        fragment: Fragment,
        outState: Bundle
    ) = log(fragment, "onFragmentSaveInstanceState", outState)

    override fun onFragmentViewDestroyed(
        manager: FragmentManager,
        fragment: Fragment
    ) = log(fragment, "onFragmentViewDestroyed")

    override fun onFragmentDestroyed(
        manager: FragmentManager,
        fragment: Fragment
    ) = log(fragment, "onFragmentDestroyed")

    override fun onFragmentDetached(
        manager: FragmentManager,
        fragment: Fragment
    ) = log(fragment, "onFragmentDetached")
}