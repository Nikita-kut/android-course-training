package com.nikita.kut.android.a17_lists_2_home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nikita.kut.android.a17_lists_2_home.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment(), MainFragment.MAIN_FRAGMENT_TAG)
                .commit()
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