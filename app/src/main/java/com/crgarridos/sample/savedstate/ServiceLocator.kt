package com.crgarridos.sample.savedstate

import android.os.Bundle
import androidx.savedstate.SavedStateRegistryOwner
import com.crgarridos.sample.savedstate.domain.SongRepository
import com.crgarridos.sample.savedstate.ui.type.basic.BasicViewModel
import com.crgarridos.sample.savedstate.ui.type.savedstateviewmodel.SavedStateHandleViewModel

object ServiceLocator {
    private fun getSongRepository(): SongRepository = SongRepository()

    fun getBasicViewModelFactory(): BasicViewModel.Factory {
        return BasicViewModel.Factory(getSongRepository())
    }

    fun getSavedStateHandleViewModelFactory(
        owner: SavedStateRegistryOwner,
        defaultArgs: Bundle?
    ): SavedStateHandleViewModel.Factory {
        return SavedStateHandleViewModel.Factory(
            getSongRepository(),
            owner,
            defaultArgs
        )
    }
}