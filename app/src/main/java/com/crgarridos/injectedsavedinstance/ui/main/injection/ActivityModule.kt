package com.crgarridos.injectedsavedinstance.ui.main.injection

import com.crgarridos.injectedsavedinstance.MainActivity
import com.crgarridos.injectedsavedinstance.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ContributesAndroidInjector
    fun injectorForMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    fun injectorForMainFragment(): MainFragment
}