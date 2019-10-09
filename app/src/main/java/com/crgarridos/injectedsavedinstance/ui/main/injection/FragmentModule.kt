package com.crgarridos.injectedsavedinstance.ui.main.injection

import androidx.savedstate.SavedStateRegistryOwner
import com.crgarridos.injectedsavedinstance.ui.main.MainFragment
import dagger.Binds
import dagger.Module

@Module interface FragmentModule {
    @Binds fun bindSavedStateRegistryOwner(f: MainFragment): SavedStateRegistryOwner
}