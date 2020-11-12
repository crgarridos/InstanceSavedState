package com.crgarridos.sample.savedstate.ui.type.savedstateviewmodel

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.crgarridos.sample.savedstate.R
import com.crgarridos.sample.savedstate.application.extensions.observeNotNull
import com.crgarridos.sample.savedstate.domain.Song
import com.crgarridos.sample.savedstate.ui.type.savedstateviewmodel.SavedStateHandleViewModel.Companion.NAME_SAVED_STATE_KEY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*

@AndroidEntryPoint
class SavedStateHandleFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance(name: String? = null): SavedStateHandleFragment = SavedStateHandleFragment().apply {
            arguments = bundleOf(NAME_SAVED_STATE_KEY to name)
        }
    }

    private val viewModel: SavedStateHandleViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.songResults.observeNotNull(viewLifecycleOwner, ::bindSongs)
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
