package com.crgarridos.injectedsavedinstance.ui.main

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.crgarridos.injectedsavedinstance.domain.Song
import com.crgarridos.injectedsavedinstance.domain.SongRepository
import java.util.ArrayList

class MainViewModel(
    private val repository: SongRepository
) : ViewModel() {

    private val _songResults = MutableLiveData<List<Song>>()
    val songResults: LiveData<List<Song>>
        get() = _songResults

    fun onInit(savedInstanceState: Bundle?) {
        _songResults.value = savedInstanceState?.getParcelableArrayList("awesome")
    }

    fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("awesome", ArrayList(songResults.value.orEmpty()))
    }

    fun search(name: String) {
        _songResults.value = repository.getSongs()
            .filter { name in it.id.toString() }
    }

    class Factory(private val repository: SongRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }
}
