package com.crgarridos.sample.savedstate.ui.type.savedstateviewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.crgarridos.sample.savedstate.domain.Song
import com.crgarridos.sample.savedstate.domain.SongRepository


class SavedStateHandleViewModel @ViewModelInject constructor(
    private val repository: SongRepository,
    @Assisted private val handle: SavedStateHandle
) : ViewModel() {

    companion object {
        const val NAME_SAVED_STATE_KEY = "name"
    }

    val songResults: LiveData<List<Song>> = handle.getLiveData<String?>(NAME_SAVED_STATE_KEY).switchMap { name ->
        liveData {
            if (!name.isNullOrBlank())
                emit(repository.getSongsByName(name))
        }
    }

    fun search(name: String) {
        handle.set(NAME_SAVED_STATE_KEY, name)
    }
}