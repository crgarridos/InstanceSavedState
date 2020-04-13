package com.crgarridos.sample.savedstate.ui

import android.os.Bundle
import com.crgarridos.sample.savedstate.R
import com.crgarridos.sample.savedstate.ui.savedstateviewmodel.SavedStateHandleFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(R.layout.main_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SavedStateHandleFragment.newInstance())
                .commitNow()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("awesome2", 1313)
    }

}
