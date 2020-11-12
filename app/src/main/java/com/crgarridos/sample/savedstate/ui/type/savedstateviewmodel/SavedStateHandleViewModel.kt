package com.crgarridos.sample.savedstate.ui.type.savedstateviewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.crgarridos.sample.savedstate.application.extensions.savedState
import com.crgarridos.sample.savedstate.domain.Song
import com.crgarridos.sample.savedstate.domain.SongRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


private const val NAME_SAVED_STATE_KEY = "name"

class SavedStateHandleViewModel @ViewModelInject constructor(
    private val repository: SongRepository,
    @Assisted private val handle: SavedStateHandle
) : ViewModel() {

    private var searchJob: Job? = null
    val songResults: LiveData<List<Song>> = handle.getLiveData<String>(NAME_SAVED_STATE_KEY).switchMap { name ->
        liveData {
            emit(repository.getSongsByName(name))
        }
    }

    fun search(name: String) {
        searchJob?.cancel()
        handle.set(NAME_SAVED_STATE_KEY, name)
    }
}