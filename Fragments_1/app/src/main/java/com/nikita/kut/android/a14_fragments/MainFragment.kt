package com.nikita.kut.android.a14_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

class MainFragment : Fragment(), ListFragment.ClickListener,
    DetailFragment.ButtonBackClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null)
            childFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, ListFragment.newInstance())
                .commit()
    }

    override fun onCardClick(text: String) {
        childFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, DetailFragment.newInstance(text))
            .addToBackStack(null)
            .commit()
    }


    override fun onButtonBackClick() {
        childFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, ListFragment.newInstance())
            .commit()

    }
}