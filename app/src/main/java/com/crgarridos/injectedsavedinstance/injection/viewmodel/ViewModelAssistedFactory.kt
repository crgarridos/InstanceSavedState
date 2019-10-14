package com.crgarridos.injectedsavedinstance.injection.viewmodel

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import javax.inject.Inject

interface ViewModelAssistedFactory<T: ViewModel> {
    fun create(handle: SavedStateHandle): T
}

class ViewModelFactory<VM, AF> @Inject constructor(
    private val factory: AF, owner: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(owner, null)
        where VM: ViewModel, AF: ViewModelAssistedFactory<VM> {

    override fun <VM : ViewModel?> create(
        key: String,
        modelClass: Class<VM>,
        handle: SavedStateHandle
    ): VM {
        return factory.create(handle) as VM
    }
}