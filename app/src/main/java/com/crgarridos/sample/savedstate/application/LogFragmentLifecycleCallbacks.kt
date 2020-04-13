package com.crgarridos.sample.savedstate.application

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
    ) = log(fragment, "onPreAttach")

    override fun onFragmentAttached(
        manager: FragmentManager,
        fragment: Fragment,
        context: Context
    ) = log(fragment, "onAttach")

    override fun onFragmentCreated(
        manager: FragmentManager,
        fragment: Fragment,
        savedInstanceState: Bundle?
    ) = log(fragment, "onCreate", savedInstanceState)

    override fun onFragmentActivityCreated(
        manager: FragmentManager,
        fragment: Fragment,
        savedInstanceState: Bundle?
    ) = log(fragment, "onActivityCreated", savedInstanceState)

    override fun onFragmentViewCreated(
        manager: FragmentManager,
        fragment: Fragment,
        v: View,
        savedInstanceState: Bundle?
    ) = log(fragment, "onViewCreated", savedInstanceState)

    override fun onFragmentStarted(
        manager: FragmentManager,
        fragment: Fragment
    ) = log(fragment, "onStart")

    override fun onFragmentResumed(
        manager: FragmentManager,
        fragment: Fragment
    ) = log(fragment, "onResume")

    override fun onFragmentPaused(
        manager: FragmentManager,
        fragment: Fragment
    ) = log(fragment, "onPause")

    override fun onFragmentStopped(
        manager: FragmentManager,
        fragment: Fragment
    ) = log(fragment, "onStop")

    override fun onFragmentSaveInstanceState(
        manager: FragmentManager,
        fragment: Fragment,
        outState: Bundle
    ) = log(fragment, "onSaveInstanceState", outState)

    override fun onFragmentViewDestroyed(
        manager: FragmentManager,
        fragment: Fragment
    ) = log(fragment, "onDestroyView")

    override fun onFragmentDestroyed(
        manager: FragmentManager,
        fragment: Fragment
    ) = log(fragment, "onDestroy")

    override fun onFragmentDetached(
        manager: FragmentManager,
        fragment: Fragment
    ) = log(fragment, "onDetach")
}