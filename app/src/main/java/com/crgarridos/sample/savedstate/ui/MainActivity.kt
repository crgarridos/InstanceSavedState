package com.crgarridos.sample.savedstate.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.crgarridos.sample.savedstate.R
import com.crgarridos.sample.savedstate.ui.type.basic.BasicFragment
import com.crgarridos.sample.savedstate.ui.type.savedstateviewmodel.SavedStateHandleFragment
import kotlinx.android.synthetic.main.main_activity.*
import timber.log.Timber

class MainActivity : AppCompatActivity(R.layout.main_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.tag("Lifecycle bonus").d("MainActivity.onCreate extras=${intent.extras}")
        if (intent.extras == null)
            intent.putExtra("extrasString", "extrasString")
        fragmentTypeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.basicSampleButton -> setFragment(BasicFragment.newInstance())
                R.id.savedStateHandleSampleButton -> setFragment(SavedStateHandleFragment.newInstance())
            }
        }
    }

    private fun setFragment(fragment: Fragment) {
        val tag = fragment::class.qualifiedName
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .addToBackStack(tag)
                .replace(R.id.container, fragment, tag)
                .commit()
        } else {
            val f = supportFragmentManager.findFragmentByTag(tag)!!
            Timber.tag("Lifecycle bonus").d("setFragment arguments ${f.arguments}")

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("awesome2", 1313)
    }

}
