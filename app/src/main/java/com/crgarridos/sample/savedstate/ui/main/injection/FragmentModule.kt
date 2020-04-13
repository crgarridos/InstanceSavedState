package com.crgarridos.sample.savedstate.ui.main.injection

import androidx.savedstate.SavedStateRegistryOwner
import com.crgarridos.sample.savedstate.ui.main.MainFragment
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module

@AssistedModule
@Module(includes = [AssistedInject_FragmentModule::class])
interface FragmentModule {
    @Binds fun bindSavedStateRegistryOwner(f: MainFragment): SavedStateRegistryOwner
}