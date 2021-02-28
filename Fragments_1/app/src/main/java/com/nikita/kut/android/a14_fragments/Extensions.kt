package com.nikita.kut.android.a14_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment

fun <T : Fragment> T.withArguments(action: Bundle.() -> Unit): T {
    return apply {
        val argumentsForAdd = Bundle().apply(action)
        arguments = argumentsForAdd
    }
}