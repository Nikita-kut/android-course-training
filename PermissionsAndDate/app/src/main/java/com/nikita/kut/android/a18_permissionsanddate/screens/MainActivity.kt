package com.nikita.kut.android.a18_permissionsanddate.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nikita.kut.android.a18_permissionsanddate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.activityMainContainer.id, MainFragment())
                .commit()
        }
    }
}