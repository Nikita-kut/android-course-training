package com.nikita.kut.android.a17_lists_2_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nikita.kut.android.a17_lists_2_home.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btnVertical.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, ListFragment.newInstance(KEY_VERTICAL))
                ?.addToBackStack(null)
                ?.commit()
        }

        binding.btnHorizontal.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, ListFragment.newInstance(KEY_HORIZONTAL))
                ?.addToBackStack(null)
                ?.commit()
        }

        binding.btnGrid.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, ListFragment.newInstance(KEY_GRID))
                ?.addToBackStack(null)
                ?.commit()
        }

        binding.btnStaggeredGrid.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, ListFragment.newInstance(KEY_STAGGERED_GRID))
                ?.addToBackStack(null)
                ?.commit()
        }
        binding.btnPagination.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, ListFragment.newInstance(KEY_PAGINATION))
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val MAIN_FRAGMENT_TAG = "main_fragment_tag"
        const val KEY_VERTICAL = 1
        const val KEY_HORIZONTAL = 2
        const val KEY_GRID = 3
        const val KEY_STAGGERED_GRID = 4
        const val KEY_PAGINATION = 5
    }

}