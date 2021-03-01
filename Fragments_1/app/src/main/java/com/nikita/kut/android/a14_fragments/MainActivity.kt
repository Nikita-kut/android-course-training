package com.nikita.kut.android.a14_fragments

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

private val loginFragment = LoginFragment()

class MainActivity : AppCompatActivity(), LoginFragment.ClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, loginFragment)
                .commit()
        }


    }

    override fun onBackPressed() {
        val backStackCount =
            supportFragmentManager.findFragmentByTag(getString(R.string.main_fragment))?.childFragmentManager?.backStackEntryCount
        if (backStackCount == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.findFragmentByTag("main_fragment")?.childFragmentManager?.popBackStack()
        }
    }

    override fun onLoginClick() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment())
            .commit()
    }

}