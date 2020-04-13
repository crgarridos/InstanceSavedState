package com.crgarridos.sample.savedstate.ui.injection

import com.crgarridos.sample.savedstate.ui.MainActivity
import com.crgarridos.sample.savedstate.ui.type.basic.BasicFragment
import com.crgarridos.sample.savedstate.ui.type.savedstateviewmodel.SavedStateHandleFragment
import com.crgarridos.sample.savedstate.ui.type.savedstateviewmodel.injection.SavedStateHandleFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ContributesAndroidInjector
    fun injectorForMainActivity(): MainActivity

    @ContributesAndroidInjector()
    fun injectorForBasicFragment(): BasicFragment

    @ContributesAndroidInjector(modules = [SavedStateHandleFragmentModule::class])
    fun injectorForSavedStateHandleFragmentFragment(): SavedStateHandleFragment
}