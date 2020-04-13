package com.crgarridos.sample.savedstate.ui.type.savedstateviewmodel

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.crgarridos.sample.savedstate.R
import com.crgarridos.sample.savedstate.domain.Song
import com.crgarridos.sample.savedstate.application.extensions.observeNotNull
import com.crgarridos.sample.savedstate.application.injection.viewmodel.SavedStateViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class SavedStateHandleFragment : DaggerFragment(R.layout.main_fragment) {

    companion object {
        fun newInstance(): SavedStateHandleFragment =
            SavedStateHandleFragment()
    }

    @Inject lateinit var factory: SavedStateViewModelFactory<SavedStateHandleViewModel, SavedStateHandleViewModel.AssistedFactory>
    private val viewModel: SavedStateHandleViewModel by viewModels { factory }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.songResults.observeNotNull(viewLifecycleOwner) {
            bindSongs(it)
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        searchButton.setOnClickListener {
            viewModel.search(searchField.text.toString())
        }
    }

    private fun bindSongs(songs: List<Song>) {
        message.text = songs.joinToString("\n")
    }
}
