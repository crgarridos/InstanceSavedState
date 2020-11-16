package com.crgarridos.sample.savedstate.ui.type.savedstateviewmodel

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.crgarridos.sample.savedstate.domain.Song
import com.crgarridos.sample.savedstate.domain.SongRepository


class SavedStateHandleViewModel(
    private val repository: SongRepository,
    private val handle: SavedStateHandle
) : ViewModel() {

    companion object {
        const val NAME_SAVED_STATE_KEY = "name"
    }

    val songResults: LiveData<List<Song>> =
        handle.getLiveData<String?>(NAME_SAVED_STATE_KEY).switchMap { name ->
            liveData {
                if (!name.isNullOrBlank())
                    emit(repository.getSongsByName(name))
            }
        }

    fun search(name: String) {
        handle.set(NAME_SAVED_STATE_KEY, name)
    }

    class Factory(
        private val repository: SongRepository,
        private val owner: SavedStateRegistryOwner,
        private val defaultArgs: Bundle?
    ) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T = SavedStateHandleViewModel(repository, handle) as T

    }
}