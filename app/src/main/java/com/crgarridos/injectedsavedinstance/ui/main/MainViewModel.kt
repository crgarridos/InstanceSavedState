package com.crgarridos.injectedsavedinstance.ui.main

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.crgarridos.injectedsavedinstance.domain.Song
import com.crgarridos.injectedsavedinstance.domain.SongRepository


class MainViewModel(
    private val repository: SongRepository
) : ViewModel() {

    private val instanceState: Bundle = Bundle()

    private val _songResults = MutableLiveData<List<Song>>()
    val songResults: LiveData<List<Song>>
        get() = _songResults

    fun onInit(savedInstanceState: Bundle) {
        instanceState.putAll(savedInstanceState)
        instanceState.getString("name")
            ?.let(::search)
    }

    fun onSaveInstanceState(outState: Bundle) {
        outState.putAll(instanceState)
    }

    fun search(name: String) {
        instanceState.putString("name", name)
        _songResults.value = repository.getSongs()
            .filter { name in it.id.toString() }
    }

    class Factory(private val repository: SongRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }
}
