package com.crgarridos.sample.savedstate.ui.type.savedstateviewmodel.injection

import androidx.savedstate.SavedStateRegistryOwner
import com.crgarridos.sample.savedstate.ui.type.savedstateviewmodel.SavedStateHandleFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent


@Module @InstallIn(FragmentComponent::class)
interface SavedStateHandleFragmentModule {
    @Binds fun bindSavedStateRegistryOwner(f: SavedStateHandleFragment): SavedStateRegistryOwner
}