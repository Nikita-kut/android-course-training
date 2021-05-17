package com.nikita.kut.android.a14_fragments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.view.children
import androidx.fragment.app.Fragment

class ListFragment : Fragment(R.layout.fragment_list) {

    lateinit var et1: EditText
    lateinit var et2: EditText
    lateinit var et3: EditText
    lateinit var et4: EditText
    lateinit var et5: EditText
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()

        view.let {
            it as ViewGroup }
            .children
            .mapNotNull { it as? ImageButton }
            .forEach { button ->
                button.setOnClickListener {
                    (parentFragment as ClickListener).onCardClick(getEtText())
                }
            }
    }

    private fun setViews() {
        et1 = view?.findViewById(R.id.edit_text_1) ?: throw RuntimeException()
        et2 = view?.findViewById(R.id.edit_text_2) ?: throw RuntimeException()
        et3 = view?.findViewById(R.id.edit_text_3) ?: throw RuntimeException()
        et4 = view?.findViewById(R.id.edit_text_4) ?: throw RuntimeException()
        et5 = view?.findViewById(R.id.edit_text_5) ?: throw RuntimeException()
    }

    interface ClickListener {
        fun onCardClick(text: String)
    }

    private fun getEtText(): String {
        val arrayEt = listOf(et1, et2, et3, et4, et5)
        var etText = ""
        for (current in arrayEt.indices) {
            if (arrayEt[current].text.isNotBlank()) {
                etText = arrayEt[current].text.toString()
            }
        }
        return etText
    }

    companion object {
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }
}
