package com.crgarridos.injectedsavedinstance

import android.os.Bundle
import android.util.Log
import com.crgarridos.injectedsavedinstance.ui.main.MainFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        Log.d("Activity", "onCreate: $savedInstanceState")
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("awesome2", 1313)
        Log.d("Activity", "onSaveInstanceState: $outState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("Activity", "onRestoreInstanceState: $savedInstanceState")
    }

}
