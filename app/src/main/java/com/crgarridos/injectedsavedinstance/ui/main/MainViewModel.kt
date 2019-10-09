package com.crgarridos.injectedsavedinstance.ui.main

import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.crgarridos.injectedsavedinstance.domain.Song
import com.crgarridos.injectedsavedinstance.domain.SongRepository
import javax.inject.Inject


class MainViewModel(
    private val repository: SongRepository,
    private val handle: SavedStateHandle
) : ViewModel() {

    private val _songResults = MutableLiveData<List<Song>>()
    val songResults: LiveData<List<Song>>
        get() = _songResults

    init {
        handle.get<String>("name")
            ?.let(::search)
    }

    fun search(name: String) {
        handle.set("name", name)
        _songResults.value = repository.getSongs()
            .filter { name in it.id.toString() }
    }

    class Factory @Inject constructor(
        private val repository: SongRepository,
        private val owner: SavedStateRegistryOwner
    ) : AbstractSavedStateViewModelFactory(owner, null) {
        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return MainViewModel(repository, handle) as T
        }
    }
}


///TODO livedata from saved state lib