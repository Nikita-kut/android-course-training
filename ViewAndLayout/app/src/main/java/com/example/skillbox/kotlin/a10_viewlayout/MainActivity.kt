package com.example.skillbox.kotlin.a10_viewlayout

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.marginTop
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val passwordPattern =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.isEnabled = false
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked && emailInput.text.isNotEmpty() && passwordInput.text.isNotEmpty()) {
                loginButton.isEnabled = true
            }
        }
        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            loginClick()

        }
    }

    private fun loginClick() {
        val email = emailInput.text.toString().trim()
        val password = passwordInput.text.toString().trim()

        fun emailValidation(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun passwordValidation(password: String): Boolean {
            return password.matches(passwordPattern.toRegex())
        }

        val progressBarLoader = ProgressBar(this).apply {
            ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
        }
        container.addView(progressBarLoader)

        loginButton.isEnabled = false
        emailInput.isEnabled = false
        passwordInput.isEnabled = false
        checkBox.isEnabled = false

        if (emailValidation(email) && passwordValidation(password)) {
            Handler().postDelayed({
                container.removeView(progressBarLoader)
                loginButton.isEnabled = false
                emailInput.isEnabled = true
                passwordInput.isEnabled = true
                checkBox.isEnabled = true
                checkBox.isChecked = false
                Toast.makeText(this, R.string.loader_operation, Toast.LENGTH_SHORT).show()
            }, 2000)
        } else {
            container.removeView(progressBarLoader)
            loginButton.isEnabled = false
            emailInput.isEnabled = true
            passwordInput.isEnabled = true
            checkBox.isEnabled = true
            checkBox.isChecked = false
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
        }
    }

}
