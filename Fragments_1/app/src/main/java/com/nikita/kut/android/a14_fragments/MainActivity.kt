package com.nikita.kut.android.a14_fragments

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

private val loginFragment = LoginFragment()

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    val isTablet: Boolean by lazy { resources.getBoolean(R.bool.isTablet) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, loginFragment)
                .commit()
        }
    }

    override fun onBackPressed() {
        val backStackCount =
            supportFragmentManager.findFragmentByTag(MainFragment.MAIN_FRAGMENT_TAG)?.childFragmentManager?.backStackEntryCount
        if (backStackCount == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.findFragmentByTag(MainFragment.MAIN_FRAGMENT_TAG)?.childFragmentManager?.popBackStack()
        }
    }
}