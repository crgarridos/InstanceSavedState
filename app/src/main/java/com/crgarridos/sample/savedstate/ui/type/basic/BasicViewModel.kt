package com.crgarridos.sample.savedstate.ui.type.basic

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crgarridos.sample.savedstate.domain.Song
import com.crgarridos.sample.savedstate.domain.SongRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BasicViewModel @ViewModelInject constructor(
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
}