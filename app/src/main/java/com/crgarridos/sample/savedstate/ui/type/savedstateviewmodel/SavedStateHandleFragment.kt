package com.crgarridos.sample.savedstate.ui.type.savedstateviewmodel

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
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
            SavedStateHandleFragment().apply {
                arguments  = bundleOf("fragmentArguments" to "fragmentArgument")
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @Inject lateinit var factory: SavedStateViewModelFactory<SavedStateHandleViewModel, SavedStateHandleViewModel.AssistedFactory>
    private val viewModel: SavedStateHandleViewModel by viewModels { factory }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.songResults.observeNotNull(viewLifecycleOwner, ::bindSongs)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        println("")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        searchField.doOnTextChanged { text, _, _, _ ->
            viewModel.search(text?.toString().orEmpty())
        }
    }

    private fun bindSongs(songs: List<Song>) {
        message.text = songs.joinToString("\n")
    }
}
