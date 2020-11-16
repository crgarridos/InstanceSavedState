package com.crgarridos.sample.savedstate.ui.type.basic

import androidx.lifecycle.*
import com.crgarridos.sample.savedstate.domain.Song
import com.crgarridos.sample.savedstate.domain.SongRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BasicViewModel(
    private val repository: SongRepository
) : ViewModel() {

    private var searchJob: Job? = null
    private val _songResults = MutableLiveData<List<Song>>()
    val songResults: LiveData<List<Song>>
        get() = _songResults

    fun search(name: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _songResults.value = repository.getSongsByName(name)
        }
    }

    class Factory(private val repository: SongRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return BasicViewModel(repository) as T
        }
    }
}