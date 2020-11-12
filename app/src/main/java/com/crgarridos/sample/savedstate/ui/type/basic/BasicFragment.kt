package com.crgarridos.sample.savedstate.ui.type.basic

import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.crgarridos.sample.savedstate.R
import com.crgarridos.sample.savedstate.application.extensions.observeNotNull
import com.crgarridos.sample.savedstate.domain.Song
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*

/**
 * This is a basic fragment sample. It does not manage saved instance state
 */
@AndroidEntryPoint
class BasicFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance(): BasicFragment = BasicFragment()
    }

    private val viewModel: BasicViewModel by viewModels()

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