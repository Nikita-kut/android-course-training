package com.nikita.kut.android.a13_intents

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.Guideline
import androidx.core.widget.doOnTextChanged

class LoginActivity : AppCompatActivity() {

    private val btnLogin: Button by lazy { findViewById(R.id.button_login) }
    private val etEmailInput: EditText by lazy { findViewById(R.id.et_email_input) }
    private val etPasswordInput: EditText by lazy { findViewById(R.id.et_password_input) }
    private val checkbox: CheckBox by lazy { findViewById(R.id.checkBox) }
    private val container: ConstraintLayout by lazy { findViewById(R.id.constraint) }
    private val bottomHorizontalGuideline: Guideline by lazy { findViewById(R.id.bottom_horizontal_guideline) }
    private val leftVerticalGuideline: Guideline by lazy { findViewById(R.id.leftVerticalGuideline) }
    private val rightVerticalGuideline: Guideline by lazy { findViewById(R.id.right_vertical_guideline) }
    private val textView: TextView by lazy { findViewById(R.id.tv_helper) }
    private val TAG = "MainActivity"
    private var formMessage = FormState("")
    private val passwordPattern =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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

        if (savedInstanceState != null) {
            formMessage = savedInstanceState.getParcelable(KEY_FORM_STATE)
                ?: error("UnexpectedState")
            textView.text = formMessage.message
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_FORM_STATE, formMessage)
    }

    private fun validateInputAndCheckbox() {
        btnLogin.isEnabled = checkbox.isChecked && etEmailInput.text.toString()
            .isNotEmpty() && etPasswordInput.text.toString().isNotEmpty()
    }

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

        if (validateEmail /*&& validatePassword*/) {
            Handler().postDelayed({
                textView.setText(R.string.enter_e_mail_and_password)
                container.removeView(progressBarAdd)
                setViewsEnable()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 2000)
        } else {
            formMessage.message = "Invalid email or password"
            textView.text = formMessage.message
            container.removeView(progressBarAdd)
            setViewsEnable()
            toast("Invalid email or password")
        }
    }

    private fun setViewsEnable() {
        btnLogin.isEnabled = true
        etEmailInput.isEnabled = true
        etPasswordInput.isEnabled = true
        checkbox.isEnabled = true
        checkbox.isChecked = true
    }

    private fun toast(toast: String) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private val KEY_FORM_STATE = "formstate"
    }
}