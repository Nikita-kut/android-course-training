package com.example.skillbox.kotlin.a11_01_constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginButton.isEnabled = false

        emailInput.doOnTextChanged { text, start, before, count ->
            validateInputAndCheckbox()
        }

        passwordInput.doOnTextChanged { text, start, before, count ->
            validateInputAndCheckbox()
        }

        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            validateInputAndCheckbox()
        }

        loginButton.setOnClickListener {
            loginClick()
        }
    }

    private fun validateInputAndCheckbox() {
        loginButton.isEnabled = checkBox.isChecked && emailInput.text.toString()
            .isNotEmpty() && passwordInput.text.toString().isNotEmpty()
    }

    private val passwordPattern =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"

//    private fun makeProgressBar(): ProgressBar = ProgressBar(this).apply {
//        ConstraintLayout.LayoutParams(
//            ConstraintLayout.LayoutParams.WRAP_CONTENT,
//            ConstraintLayout.LayoutParams.WRAP_CONTENT
//        )
//    }

    private fun loginClick() {
        val progressBarAdd = ProgressBar(this).apply {
            ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }
        progressBarAdd.id = View.generateViewId()

        val constraintSet = ConstraintSet()
        constraintSet.clone(container)
        constraintSet.connect(progressBarAdd.id, ConstraintSet.TOP, guideline3.id, ConstraintSet.BOTTOM)
        constraintSet.connect(progressBarAdd.id, ConstraintSet.START, guideline.id, ConstraintSet.START)
        constraintSet.connect(progressBarAdd.id, ConstraintSet.END, guideline2.id, ConstraintSet.END)
        constraintSet.applyTo(container)

        container.addView(progressBarAdd)

        val validateEmail =
            android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput.text.toString().trim()).matches()
        val validatePassword =
            passwordInput.text.toString().trim().matches(passwordPattern.toRegex())

        loginButton.isEnabled = false
        emailInput.isEnabled = false
        passwordInput.isEnabled = false
        checkBox.isEnabled = false

        if (validateEmail /*&& validatePassword*/) {
            Handler().postDelayed({
                container.removeView(progressBarAdd)
                loginButton.isEnabled = true
                emailInput.isEnabled = true
                passwordInput.isEnabled = true
                checkBox.isEnabled = true
                checkBox.isChecked = true
                Toast.makeText(this, R.string.loader_operation, Toast.LENGTH_SHORT).show()
            }, 2000)
        } else {
            container.removeView(progressBarAdd)
            loginButton.isEnabled = true
            emailInput.isEnabled = true
            passwordInput.isEnabled = true
            checkBox.isEnabled = true
            checkBox.isChecked = true
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
        }
    }

}