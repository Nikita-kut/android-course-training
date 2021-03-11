package com.nikita.kut.android.a16_lists_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nikita.kut.android.a16_lists_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ListFragment())
                .commit()
    }
}