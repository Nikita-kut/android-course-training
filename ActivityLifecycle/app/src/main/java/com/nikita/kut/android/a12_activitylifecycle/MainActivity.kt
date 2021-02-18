package com.nikita.kut.android.a12_activitylifecycle

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.Guideline
import androidx.core.widget.doOnTextChanged

class MainActivity : AppCompatActivity() {

    private val btnLogin: Button by lazy { findViewById(R.id.loginButton) }
    private val btnAnr: Button by lazy { findViewById(R.id.anr_button) }
    private val etEmailInput: EditText by lazy { findViewById(R.id.emailInput) }
    private val etPasswordInput: EditText by lazy { findViewById(R.id.passwordInput) }
    private val checkbox: CheckBox by lazy { findViewById(R.id.checkBox) }
    private val container: ConstraintLayout by lazy { findViewById(R.id.constraint) }
    private val bottomHorizontalGuideline: Guideline by lazy { findViewById(R.id.bottomHorizontalGuideline) }
    private val leftVerticalGuideline: Guideline by lazy { findViewById(R.id.leftVerticalGuideline) }
    private val rightVerticalGuideline: Guideline by lazy { findViewById(R.id.rightVerticalGuideline) }
    private val textView: TextView by lazy { findViewById(R.id.textView) }
    private val TAG = "MainActivity"
    private var formMessage = FormState("")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etEmailInput.doOnTextChanged { text, start, before, count ->
            validateInputAndCheckbox()
            if (etEmailInput.text.isEmpty()) {
                textView.setText(R.string.enter_e_mail_and_password)
            }
        }

        etPasswordInput.doOnTextChanged { text, start, before, count ->
            validateInputAndCheckbox()
            if (etEmailInput.text.isEmpty()) {
                textView.setText(R.string.enter_e_mail_and_password)
            }
        }

        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            validateInputAndCheckbox()
        }

        btnLogin.setOnClickListener {
            loginClick()
        }

        btnAnr.setOnClickListener {
            anrButtonClick()
        }
        Log.d(TAG, "On create was called")

        if (savedInstanceState != null) {
            formMessage = savedInstanceState.getParcelable<FormState>(KEY_FORM_STATE)
                ?: error("UnexpectedState")
            textView.text = formMessage.message
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_FORM_STATE, formMessage)

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "On start was called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "On resume was called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "On pause was called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "On destroy was called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "On destroy was called")
    }

    private fun anrButtonClick() {
        Thread.sleep(100000)
    }

    private fun validateInputAndCheckbox() {
        btnLogin.isEnabled = checkbox.isChecked && etEmailInput.text.toString()
            .isNotEmpty() && etPasswordInput.text.toString().isNotEmpty()
    }

    private val passwordPattern =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"

    private fun makeProgressBar(): ProgressBar {
        val progressBar = ProgressBar(this).apply {
            ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }
        progressBar.id = View.generateViewId()
        container.addView(progressBar)

        val constraintSet = ConstraintSet()
        constraintSet.clone(container)
        constraintSet.connect(
            progressBar.id,
            ConstraintSet.TOP,
            bottomHorizontalGuideline.id,
            ConstraintSet.BOTTOM
        )
        constraintSet.connect(
            progressBar.id,
            ConstraintSet.START,
            leftVerticalGuideline.id,
            ConstraintSet.START
        )
        constraintSet.connect(
            progressBar.id,
            ConstraintSet.END,
            rightVerticalGuideline.id,
            ConstraintSet.END
        )
        constraintSet.applyTo(container)
        return progressBar
    }

    private fun loginClick() {
        val progressBarAdd = makeProgressBar()

        val validateEmail =
            android.util.Patterns.EMAIL_ADDRESS.matcher(etEmailInput.text.toString().trim())
                .matches()
        val validatePassword =
            etPasswordInput.text.toString().trim().matches(passwordPattern.toRegex())

        btnLogin.isEnabled = false
        etEmailInput.isEnabled = false
        etPasswordInput.isEnabled = false
        checkbox.isEnabled = false

        if (validateEmail && validatePassword) {
            Handler().postDelayed({
                container.removeView(progressBarAdd)
                btnLogin.isEnabled = true
                etEmailInput.isEnabled = true
                etPasswordInput.isEnabled = true
                checkbox.isEnabled = true
                checkbox.isChecked = true
                Toast.makeText(this, R.string.loader_operation, Toast.LENGTH_SHORT).show()
            }, 2000)
        } else {
            formMessage.message = "Invalid email or password"
            textView.text = formMessage.message
            container.removeView(progressBarAdd)
            btnLogin.isEnabled = true
            etEmailInput.isEnabled = true
            etPasswordInput.isEnabled = true
            checkbox.isEnabled = true
            checkbox.isChecked = true
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private val KEY_FORM_STATE = "formstate"
    }

}
