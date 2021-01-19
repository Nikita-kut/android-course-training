package com.example.skillbox.kotlin.a10_viewlayout

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.marginTop
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailInput.addTextChangedListener(watcher)

        passwordInput.addTextChangedListener(watcher)

        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            loginButton.isEnabled = isChecked && emailInput.text.toString()
                .isNotEmpty() && passwordInput.text.toString().isNotEmpty()
        }

        loginButton.setOnClickListener {
            loginClick()
        }
    }

    private val watcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            loginButton.isEnabled =
                emailInput.text.toString().isNotEmpty() && passwordInput.text.toString()
                    .isNotEmpty() && checkBox.isChecked
        }
    }
    private val passwordPattern =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"

    private fun loginClick() {
        val progressBarLoader = ProgressBar(this).apply {
            ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
        }
        container.addView(progressBarLoader)

        val validateEmail =
            android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput.text.toString().trim()).matches()
        val validatePassword =
            passwordInput.text.toString().trim().matches(passwordPattern.toRegex())

        loginButton.isEnabled = false
        emailInput.isEnabled = false
        passwordInput.isEnabled = false
        checkBox.isEnabled = false

        if (validateEmail && validatePassword) {
            Handler().postDelayed({
                container.removeView(progressBarLoader)
                loginButton.isEnabled = true
                emailInput.isEnabled = true
                passwordInput.isEnabled = true
                checkBox.isEnabled = true
                checkBox.isChecked = true
                Toast.makeText(this, R.string.loader_operation, Toast.LENGTH_SHORT).show()
            }, 2000)
        } else {
            container.removeView(progressBarLoader)
            loginButton.isEnabled = true
            emailInput.isEnabled = true
            passwordInput.isEnabled = true
            checkBox.isEnabled = true
            checkBox.isChecked = true
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
        }
    }

}
