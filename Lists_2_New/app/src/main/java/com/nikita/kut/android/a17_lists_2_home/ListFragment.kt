package com.nikita.kut.android.a17_lists_2_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.nikita.kut.android.a17_lists_2_home.adapters.WitcherPersonAdapter
import com.nikita.kut.android.a17_lists_2_home.data.WitcherPerson
import com.nikita.kut.android.a17_lists_2_home.databinding.FragmentListBinding
import com.nikita.kut.android.a17_lists_2_home.util.*
import jp.wasabeef.recyclerview.animators.LandingAnimator
import kotlin.random.Random

class ListFragment : Fragment(), ChoicePersonsDialogFragment.ClickListener {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = _binding!!
    private var witcherPersonAdapter by AutoClearedValue<WitcherPersonAdapter>()
    private var witcherPersons: ArrayList<WitcherPerson> = arrayListOf()
    private var scrollData = arrayListOf<WitcherPerson>()
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
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
        if (isTvVisible) binding.tvEmptyList.visibility = View.VISIBLE
    }

    private fun initList() {
        val id = arguments?.getInt(KEY_ID)

        with(binding.rvFragmentList) {
            witcherPersonAdapter = WitcherPersonAdapter { position -> deletePerson(position) }
            adapter = witcherPersonAdapter
            itemAnimator = LandingAnimator()
            setHasFixedSize(true)
            when (id) {
                MainFragment.KEY_VERTICAL -> {
                    addItemDecoration(ItemOffsetDecoration(requireContext()))
                    layoutManager = LinearLayoutManager(requireContext())
                }
                MainFragment.KEY_HORIZONTAL -> {
                    layoutManager = LinearLayoutManager(requireContext()).apply {
                        orientation = LinearLayoutManager.HORIZONTAL
                    }
                }
                MainFragment.KEY_GRID -> {
                    addItemDecoration(
                        DividerItemDecoration(
                            requireContext(),
                            DividerItemDecoration.HORIZONTAL
                        )
                    )
                    addItemDecoration(
                        DividerItemDecoration(
                            requireContext(),
                            DividerItemDecoration.VERTICAL
                        )
                    )
                    layoutManager = GridLayoutManager(requireContext(), 2)
                }
                MainFragment.KEY_STAGGERED_GRID -> {
                    layoutManager =
                        StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
                }
                MainFragment.KEY_PAGINATION -> {
                    val verticalLinearLM = LinearLayoutManager(requireContext())
                    layoutManager = verticalLinearLM
                    this.addOnScrollListener(object : PaginationScrollListener(verticalLinearLM) {

                        override fun isLastPage(): Boolean = isLastPage

                        override fun isLoading(): Boolean = isLoading

                        override fun loadMoreItems() {
                            if (scrollData.size <= 100) {
                                isLoading = true
                                getMoreData()
                            } else {
                                isLastPage = true
                                isLoading = false
                            }
                        }
                    })
                }
                else -> {
                }
            }
        }
    }

    private fun getMoreData() {
        val newScrollData =
            Array(20) { WitcherPerson.Witchers(Random.nextLong(), "Test1", 18, "Man", "Wolf", "") }
        scrollData = (scrollData + newScrollData) as ArrayList<WitcherPerson>
        isLoading = false
        witcherPersonAdapter.updateWitcherPersons(scrollData)
    }

    companion object {
        private const val KEY_LIST_WITCHER_PERSONS = "key_list_witcher_persons"
        private const val KEY_ID = "key_id"

        fun newInstance(id: Int): ListFragment {
            return ListFragment().withArguments {
                putInt(KEY_ID, id)
            }
        }
    }

}