package com.crgarridos.sample.savedstate.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.crgarridos.sample.savedstate.R
import com.crgarridos.sample.savedstate.ui.type.basic.BasicFragment
import com.crgarridos.sample.savedstate.ui.type.savedstateviewmodel.SavedStateHandleFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : DaggerAppCompatActivity(R.layout.main_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentTypeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.basicSampleButton -> setFragment(BasicFragment.newInstance())
                R.id.savedStateHandleSampleButton -> setFragment(SavedStateHandleFragment.newInstance())
            }
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, fragment::class.qualifiedName)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("awesome2", 1313)
    }

}
