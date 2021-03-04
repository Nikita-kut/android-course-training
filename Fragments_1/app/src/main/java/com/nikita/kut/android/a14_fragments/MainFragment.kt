package com.nikita.kut.android.a14_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class MainFragment : Fragment(R.layout.fragment_main), ListFragment.ClickListener,
    DetailFragment.ButtonBackClickListener {

    val isTablet: Boolean by lazy { resources.getBoolean(R.bool.isTablet) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (isTablet) {
            true -> if (savedInstanceState != null) {
                setTextViewHintInvisible(false)
            }
            false -> if (savedInstanceState == null) {
                childFragmentManager.beginTransaction()
                    .replace(R.id.main_fragment_container, ListFragment.newInstance())
                    .commit()
            }
        }
    }

    override fun onCardClick(text: String) {
        childFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            .replace(R.id.main_fragment_container, DetailFragment.newInstance(text))
            .addToBackStack(null)
            .commit()

        if (isTablet) {
            setTextViewHintInvisible(isVisible = false)
        }
    }

    override fun onButtonBackClick() {
        childFragmentManager.apply {
            beginTransaction()
                .setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                ).apply {
                    if (!isTablet) {
                        replace(R.id.main_fragment_container, ListFragment.newInstance())
                            .commit()
                        popBackStack()
                    } else {
                        popBackStack()
                    }
                }
        }
    }

    private fun setTextViewHintInvisible(isVisible: Boolean) {
        view?.findViewById<TextView>(R.id.tv_hint).apply {
            if (!isVisible) {
                this?.visibility = View.GONE
            } else {
                this?.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        const val MAIN_FRAGMENT_TAG = "main_fragment_tag"
    }
}