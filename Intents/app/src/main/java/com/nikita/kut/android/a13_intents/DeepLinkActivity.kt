package com.nikita.kut.android.a13_intents

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DeepLinkActivity : AppCompatActivity() {

    private val tvDeepLink: TextView by lazy { findViewById(R.id.tv_deeplink) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deeplink)

        intent.data?.path.let { directory ->
            tvDeepLink.text = directory
        }
    }
}