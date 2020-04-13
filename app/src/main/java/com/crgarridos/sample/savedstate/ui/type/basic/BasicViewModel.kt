package com.crgarridos.sample.savedstate.ui.type.basic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.crgarridos.sample.savedstate.domain.Song
import com.crgarridos.sample.savedstate.domain.SongRepository
import javax.inject.Inject

class BasicViewModel @Inject constructor(
    private val repository: SongRepository
) : ViewModel() {

    private val _songResults = MutableLiveData<List<Song>>()
    val songResults: LiveData<List<Song>>
        get() = _songResults

    fun search(name: String) {
        _songResults.value = repository.getSongsByName(name)
    }
}