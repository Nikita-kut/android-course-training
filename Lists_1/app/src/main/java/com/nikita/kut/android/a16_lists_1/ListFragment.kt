package com.nikita.kut.android.a16_lists_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikita.kut.android.a16_lists_1.databinding.FragmentListBinding
import java.text.FieldPosition

class ListFragment : Fragment(), ChoicePersonsDialogFragment.ClickListener {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = _binding!!
    private var witcherPersonAdapter by AutoClearedValue<WitcherPersonAdapter>()
    private var witcherPersons: ArrayList<WitcherPerson> = arrayListOf()
    private val isTvVisible
        get() = witcherPersons.size == 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
        binding.fab.setOnClickListener { addPersonToList() }
    }

    override fun onCreateClick(witcherPerson: WitcherPerson) {
        witcherPersons = (witcherPersons + (arrayListOf(witcherPerson))) as ArrayList<WitcherPerson>
        witcherPersonAdapter.updateWitcherPersons(witcherPersons)
        witcherPersonAdapter.notifyItemInserted(0)
        binding.rvFragmentList.scrollToPosition(0)
        if (!isTvVisible) binding.tvEmptyList.visibility = View.GONE
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(KEY_LIST_WITCHER_PERSONS, witcherPersons)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            witcherPersons = savedInstanceState.getParcelableArrayList(KEY_LIST_WITCHER_PERSONS)
                ?: error("witcher persons list")
            witcherPersonAdapter.updateWitcherPersons(witcherPersons)
            if (isTvVisible) binding.tvEmptyList.visibility =
                View.VISIBLE else binding.tvEmptyList.visibility = View.GONE
        }
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun addPersonToList() {
        ChoicePersonsDialogFragment().show(childFragmentManager, "choice_tag")
    }

    private fun deletePerson(position: Int) {
        witcherPersons =
            witcherPersons.filterIndexed { index, witcherPerson -> index != position } as ArrayList<WitcherPerson>
        witcherPersonAdapter.updateWitcherPersons(witcherPersons)
        witcherPersonAdapter.notifyItemRemoved(position)
        if (isTvVisible) binding.tvEmptyList.visibility = View.VISIBLE
    }

    private fun initList() {
        witcherPersonAdapter = WitcherPersonAdapter { position -> deletePerson(position) }
        with(binding.rvFragmentList) {
            adapter = witcherPersonAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    companion object {
        private const val KEY_LIST_WITCHER_PERSONS = "key_list_witcher_persons"
        private const val KEY_VIEW_VISIBLE = "key_view_visible"
    }

}