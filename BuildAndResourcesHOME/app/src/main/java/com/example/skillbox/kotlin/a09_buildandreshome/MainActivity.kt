package com.example.skillbox.kotlin.a09_buildandreshome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = """
            Flavor = ${BuildConfig.FLAVOR}
            Build Type = ${BuildConfig.BUILD_TYPE}
            Version Code = ${BuildConfig.VERSION_CODE}
            Version Name = ${BuildConfig.VERSION_NAME}
            ApplicationID = ${BuildConfig.APPLICATION_ID}
        """
    }
}