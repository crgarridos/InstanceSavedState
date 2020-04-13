package com.crgarridos.sample.savedstate.ui.injection

import com.crgarridos.sample.savedstate.ui.MainActivity
import com.crgarridos.sample.savedstate.ui.savedstateviewmodel.SavedStateHandleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ContributesAndroidInjector
    fun injectorForMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    fun injectorForMainFragment(): SavedStateHandleFragment
}