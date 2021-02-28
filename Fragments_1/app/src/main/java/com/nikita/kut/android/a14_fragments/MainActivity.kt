package com.nikita.kut.android.a14_fragments

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

private val loginFragment = LoginFragment()

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, loginFragment)
                .commit()
        }
    }

}