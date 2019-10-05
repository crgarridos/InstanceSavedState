package com.crgarridos.injectedsavedinstance.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.crgarridos.injectedsavedinstance.R
import com.crgarridos.injectedsavedinstance.domain.SongRepository
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = MainViewModel.Factory(SongRepository())

        viewModel = ViewModelProviders.of(this,  factory).get(MainViewModel::class.java)
        viewModel.songResults.observe(this, Observer {
            it ?: return@Observer

            message.text = it.joinToString("\n")
        })
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        searchButton.setOnClickListener {
            viewModel.search(searchField.text.toString())
        }
        Log.d("onViewStateRestored", savedInstanceState.toString())
    }

}
