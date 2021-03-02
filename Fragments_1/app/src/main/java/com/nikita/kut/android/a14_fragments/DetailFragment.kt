package com.nikita.kut.android.a14_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailFragment : Fragment(R.layout.fragment_detail) {

    lateinit var btnBack: Button
    lateinit var tvName: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnBack = view.findViewById(R.id.button_back)
        btnBack.setOnClickListener {
            (parentFragment as ButtonBackClickListener).onButtonBackClick()
        }
        tvName = view.findViewById(R.id.tv_name)
        tvName.text = arguments?.getString(KEY_TEXT)
    }

    interface ButtonBackClickListener {
        fun onButtonBackClick()
    }

    companion object {
        private const val KEY_TEXT = "key_text"

        fun newInstance(text: String): DetailFragment {
            return DetailFragment().withArguments {
                putString(KEY_TEXT, text)
            }
        }
    }

}