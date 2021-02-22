package com.nikita.kut.android.a13_intents

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged

class MainActivity : AppCompatActivity() {

    private val btnCall: Button by lazy { findViewById(R.id.button_call) }
    private val etPhoneNumber: EditText by lazy { findViewById(R.id.et_phone_number) }
    private val ivCallResultOk: ImageView by lazy { findViewById(R.id.iv_call_result_ok) }
    private val ivCallResultNo: ImageView by lazy { findViewById(R.id.iv_call_result_no) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCall.setOnClickListener {
            callClick()
        }

        etPhoneNumber.doOnTextChanged { text, start, before, count ->
            ivCallResultNo.visibility = View.GONE
            ivCallResultOk.visibility = View.GONE
        }
    }

    private fun callClick() {
        val phoneNumber: String = etPhoneNumber.text.toString()

        val isPhoneNumberValid = Patterns.PHONE.matcher(phoneNumber).matches()

        val isPhonePermissionGranted = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED

        if (!isPhoneNumberValid) {
            toast("No format phone")
        } else {
            if (!isPhonePermissionGranted) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CALL_PHONE),
                    REQUEST_PHONE
                )
            } else {
                val phoneIntent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$phoneNumber")
                    putExtra(Intent.EXTRA_PHONE_NUMBER, phoneNumber)
                }
                if (phoneIntent.resolveActivity(packageManager) != null) {
                    startActivityForResult(phoneIntent, REQUEST_PHONE)
                } else {
                    toast("No component to handle intent")
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_PHONE) {
            if (resultCode == Activity.RESULT_OK) {
                ivCallResultNo.visibility = View.GONE
                ivCallResultOk.visibility = View.VISIBLE
            } else {
                ivCallResultNo.visibility = View.VISIBLE
                ivCallResultOk.visibility = View.GONE
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun toast(toast: String) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val REQUEST_PHONE = 66
    }
}