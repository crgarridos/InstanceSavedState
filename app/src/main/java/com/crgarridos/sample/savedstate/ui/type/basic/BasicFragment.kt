package com.crgarridos.sample.savedstate.ui.type.basic

import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.crgarridos.sample.savedstate.R
import com.crgarridos.sample.savedstate.domain.Song
import com.crgarridos.sample.savedstate.application.extensions.observeNotNull
import com.crgarridos.sample.savedstate.application.injection.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

/**
 * This is a basic fragment sample. It does not manage saved instance state
 */
class BasicFragment : DaggerFragment(R.layout.main_fragment) {

    companion object {
        fun newInstance(): BasicFragment = BasicFragment()
    }

    @Inject lateinit var factory: ViewModelFactory<BasicViewModel>
    private val viewModel: BasicViewModel by viewModels { factory }

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