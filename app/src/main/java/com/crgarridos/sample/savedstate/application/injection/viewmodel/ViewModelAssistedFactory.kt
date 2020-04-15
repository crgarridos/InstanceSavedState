package com.crgarridos.sample.savedstate.application.injection.viewmodel

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import javax.inject.Inject

interface ViewModelAssistedFactory<T: ViewModel> {
    fun create(handle: SavedStateHandle): T
}

class SavedStateViewModelFactory<VM, AssistedFactory> @Inject constructor(
    owner: SavedStateRegistryOwner,
    private val factory: AssistedFactory
) : AbstractSavedStateViewModelFactory(owner, null)
        where VM: ViewModel, AssistedFactory: ViewModelAssistedFactory<VM> {

    override fun <VM : ViewModel?> create(
        key: String,
        modelClass: Class<VM>,
        handle: SavedStateHandle
    ): VM = factory.create(handle) as VM
}