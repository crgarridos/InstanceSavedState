package com.crgarridos.injectedsavedinstance.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.crgarridos.injectedsavedinstance.R
import com.crgarridos.injectedsavedinstance.domain.Song
import com.crgarridos.injectedsavedinstance.domain.SongRepository
import com.crgarridos.injectedsavedinstance.extensions.observeNotNull
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        Log.d("Fragment", "onCreateView: $savedInstanceState")
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Fragment", "onViewCreated: $savedInstanceState")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = MainViewModel.Factory(SongRepository())

        viewModel = ViewModelProviders.of(this,  factory).get(MainViewModel::class.java)
        viewModel.songResults.observeNotNull(viewLifecycleOwner) {
           bindSongs(it)
        }
        Log.d("Fragment", "onActivityCreated: $savedInstanceState")
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList("awesome", ArrayList(viewModel.songResults.value.orEmpty()))
        Log.d("Fragment", "onSaveInstanceState: $outState")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        searchButton.setOnClickListener {
            viewModel.search(searchField.text.toString())
        }
        Log.d("Fragment", "onViewStateRestored: $savedInstanceState")

        savedInstanceState?.let {
            bindSongs(it.getParcelableArrayList<Song>("awesome").orEmpty())
        }

    }

    private fun bindSongs(songs: List<Song>) {
        message.text = songs.joinToString("\n")
    }
}
