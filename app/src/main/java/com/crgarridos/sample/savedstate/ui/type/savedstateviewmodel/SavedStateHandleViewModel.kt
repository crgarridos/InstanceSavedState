package com.crgarridos.sample.savedstate.ui.type.savedstateviewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.crgarridos.sample.savedstate.application.extensions.savedState
import com.crgarridos.sample.savedstate.domain.Song
import com.crgarridos.sample.savedstate.domain.SongRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class SavedStateHandleViewModel @ViewModelInject constructor(
    private val repository: SongRepository,
    @Assisted private val handle: SavedStateHandle
) : ViewModel() {

    private var name by savedState<String>("name", handle)

    private var searchJob: Job? = null
    private val _songResults = MutableLiveData<List<Song>>()
    val songResults: LiveData<List<Song>>
        get() = _songResults

    init { name?.let(::search) }

    fun search(name: String) {
        this.name = name
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _songResults.value = repository.getSongsByName(name)
        }
    }
}