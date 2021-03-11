package com.nikita.kut.android.a15_fragments_dialogs.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.nikita.kut.android.a15_fragments_dialogs.R
import com.nikita.kut.android.a15_fragments_dialogs.databinding.ActivityMainBinding

class AppActivity: AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ViewpagerFragment())
                .commit()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}