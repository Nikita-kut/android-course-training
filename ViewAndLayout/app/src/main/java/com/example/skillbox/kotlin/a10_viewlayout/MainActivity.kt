package com.example.skillbox.kotlin.a10_viewlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.isEnabled = false
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                loginButton.isEnabled = true
            }
        }

        loginButton.setOnClickListener {
            loginClick()
        }
    }

    private fun loginClick() {
        val progressBarLoader = layoutInflater.inflate(R.layout.activity_loader, container, false)
        container.addView(progressBarLoader)
        loginButton.isEnabled = false
        emailInput.isEnabled = false
        passwordInput.isEnabled = false
        checkBox.isEnabled = false

        Handler().postDelayed({
            container.removeView(progressBarLoader)
            loginButton.isEnabled = true
            emailInput.isEnabled = true
            passwordInput.isEnabled = true
            checkBox.isEnabled = true
            checkBox.isChecked = false
            Toast.makeText(this, R.string.loader_operation, Toast.LENGTH_SHORT).show()
        }, 2000)
    }

}
