package com.crgarridos.sample.savedstate.ui.type.savedstateviewmodel.injection

import androidx.savedstate.SavedStateRegistryOwner
import com.crgarridos.sample.savedstate.ui.type.savedstateviewmodel.SavedStateHandleFragment
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module

@AssistedModule
@Module(includes = [AssistedInject_SavedStateHandleFragmentModule::class])
interface SavedStateHandleFragmentModule {
    @Binds fun bindSavedStateRegistryOwner(f: SavedStateHandleFragment): SavedStateRegistryOwner
}