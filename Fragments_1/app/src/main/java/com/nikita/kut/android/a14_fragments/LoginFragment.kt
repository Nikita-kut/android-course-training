package com.nikita.kut.android.a14_fragments

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.Guideline
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import java.lang.RuntimeException

class LoginFragment : Fragment() {

    lateinit var btnLogin: Button
    lateinit var etEmailInput: EditText
    lateinit var etPasswordInput: EditText
    lateinit var checkbox: CheckBox
    lateinit var container: ConstraintLayout
    lateinit var bottomHorizontalGuideline: Guideline
    lateinit var leftVerticalGuideline: Guideline
    lateinit var rightVerticalGuideline: Guideline
    lateinit var textView: TextView
    private var formMessage = FormState("")
    private val passwordPattern =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
    private val mainFragment = MainFragment()
    private var listener: ClickListener? = null

    override fun onAttach(context: Context) {

        super.onAttach(context)
        if (context is ClickListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()

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
            listener?.onLoginClick()
        }
    }

    private fun validateInputAndCheckbox() {
        btnLogin.isEnabled = checkbox.isChecked && etEmailInput.text.toString()
            .isNotEmpty() && etPasswordInput.text.toString().isNotEmpty()
    }

    private fun makeProgressBar(): ProgressBar {
        val progressBar = ProgressBar(activity).apply {
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

    private fun onLoginClick() {
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

    private fun setViews() {
        btnLogin = view?.findViewById(R.id.button_login) ?: throw RuntimeException()
        etEmailInput = view?.findViewById(R.id.et_email_input) ?: throw RuntimeException()
        etPasswordInput = view?.findViewById(R.id.et_password_input) ?: throw RuntimeException()
        checkbox = view?.findViewById(R.id.checkBox) ?: throw RuntimeException()
        textView = view?.findViewById(R.id.tv_helper) ?: throw RuntimeException()
        container = view?.findViewById(R.id.constraint) ?: throw RuntimeException()
        bottomHorizontalGuideline = view?.findViewById(R.id.bottom_horizontal_guideline)
            ?: throw RuntimeException()
        leftVerticalGuideline = view?.findViewById(R.id.leftVerticalGuideline)
            ?: throw RuntimeException()
        rightVerticalGuideline = view?.findViewById(R.id.right_vertical_guideline)
            ?: throw RuntimeException()
    }

    private fun toast(toast: String) {
        Toast.makeText(activity, toast, Toast.LENGTH_SHORT).show()
    }

    interface ClickListener {
        fun onLoginClick()
    }

}