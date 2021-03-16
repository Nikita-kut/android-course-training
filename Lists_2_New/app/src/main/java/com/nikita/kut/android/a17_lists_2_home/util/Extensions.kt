package com.nikita.kut.android.a17_lists_2_home.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

fun <T : Fragment> T.withArguments(action: Bundle.() -> Unit): T {
    return apply {
        val argumentsForAdd = Bundle().apply(action)
        arguments = argumentsForAdd
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}