package com.crgarridos.sample.savedstate.ui.main.injection

import com.crgarridos.sample.savedstate.MainActivity
import com.crgarridos.sample.savedstate.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ContributesAndroidInjector
    fun injectorForMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    fun injectorForMainFragment(): MainFragment
}