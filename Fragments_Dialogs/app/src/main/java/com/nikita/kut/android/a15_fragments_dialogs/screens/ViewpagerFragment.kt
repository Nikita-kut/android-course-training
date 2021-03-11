package com.nikita.kut.android.a15_fragments_dialogs.screens

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nikita.kut.android.a15_fragments_dialogs.R
import com.nikita.kut.android.a15_fragments_dialogs.databinding.FragmentViewpagerBinding
import com.nikita.kut.android.a15_fragments_dialogs.model.ArticleScreen
import com.nikita.kut.android.a15_fragments_dialogs.model.ArticleTag
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlin.math.abs
import kotlin.random.Random

class ViewpagerFragment : Fragment(R.layout.fragment_viewpager), ArticleFragment.ClickListener,
    FilterTagsDialogFragment.ConfirmClickListener {

    private var _binding: FragmentViewpagerBinding? = null
    private val binding: FragmentViewpagerBinding
        get() = _binding!!
    private val adapter by lazy { ArticleAdapter(this, screens) }
    private var tagsAllChecked = booleanArrayOf(true, true, true)
    private var screens: ArrayList<ArticleScreen> = arrayListOf(
        (ArticleScreen(
            drawableRes = R.drawable.ic_article_drawable_1,
            stringRes = R.string.article_string_1,
            tags = listOf(ArticleTag.OTHER)
        )),
        (ArticleScreen(
            R.drawable.ic_article_drawable_2,
            R.string.article_string_2,
            listOf(ArticleTag.FRUIT)
        )),
        (ArticleScreen(
            R.drawable.ic_article_drawable_3,
            R.string.article_string_3,
            listOf(ArticleTag.ANIMALS)
        )),
        (ArticleScreen(
            R.drawable.ic_article_drawable_4,
            R.string.article_string_4,
            listOf(ArticleTag.OTHER)
        )),
        (ArticleScreen(
            R.drawable.ic_article_drawable_5,
            R.string.article_string_5,
            listOf(ArticleTag.OTHER)
        )),
        (ArticleScreen(
            R.drawable.ic_article_drawable_6,
            R.string.article_string_6,
            listOf(ArticleTag.OTHER)
        )),
        (ArticleScreen(
            R.drawable.ic_article_drawable_7,
            R.string.article_string_7,
            listOf(ArticleTag.OTHER)
        )),
        (ArticleScreen(
            R.drawable.ic_article_drawable_8,
            R.string.article_string_8,
            listOf(ArticleTag.FRUIT)
        ))
    )
    private var filteredList: ArrayList<ArticleScreen> = screens

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("viewpager", "on create")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("viewpager", "on create view")
        _binding = FragmentViewpagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewPager.adapter = adapter

        setDotsIndicator(binding.viewPager)

        setPageAnimation(binding.viewPager)

        setTabLayout(binding.viewPager)

        removeBadge()

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.filter_tags -> {
                    FilterTagsDialogFragment.dialogFragmentNewInstance(tagsAllChecked)
                        .show(childFragmentManager, "filter dialog fragment")
                    true
                }
                else -> false
            }
        }
    }

    override fun onPause() {
        Log.d("viewpager", "on pause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("viewpager", "on stop")
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(KEY_FILTERED_LIST, filteredList)
        outState.putBooleanArray(KEY_BOOLEAN_ARRAY, tagsAllChecked)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            tagsAllChecked = savedInstanceState.getBooleanArray(KEY_BOOLEAN_ARRAY)
                ?: error("boolean array state error")
            filteredList =
                savedInstanceState.getParcelableArrayList(KEY_FILTERED_LIST)
                    ?: error("article array state error")
            adapter.updateList(filteredList)
        }
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onDestroyView() {
        Log.d("viewpager", "on destroy view")
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        Log.d("viewpager", "on destroy")
        super.onDestroy()
    }

    override fun onConfirmClick(selectedItems: ArrayList<String>) {

        filteredList = screens.filter { article ->
            article.tags.map { tag -> tag.name }.intersect(selectedItems.toSet()).isNotEmpty()
        } as ArrayList<ArticleScreen>

        adapter.updateList(filteredList)
    }

    private fun setDotsIndicator(viewPager: ViewPager2) {
        val dotsIndicator = requireView().findViewById<WormDotsIndicator>(R.id.worm_dots_indicator)
        dotsIndicator.setViewPager2(viewPager)
    }

    private fun setPageAnimation(viewPager: ViewPager2) {
        viewPager.setPageTransformer { page, position ->
            page.translationX = -position * page.width
            page.alpha = 1 - abs(position)
        }
    }

    private fun setTabLayout(viewPager: ViewPager2) {
        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            when (adapter.getItemId(position)) {
                screens[0].stringRes.toLong() -> tab.text =
                    getString(R.string.article_short_title_1)
                screens[1].stringRes.toLong() -> tab.text =
                    getString(R.string.article_short_title_2)
                screens[2].stringRes.toLong() -> tab.text =
                    getString(R.string.article_short_title_3)
                screens[3].stringRes.toLong() -> tab.text =
                    getString(R.string.article_short_title_4)
                screens[4].stringRes.toLong() -> tab.text =
                    getString(R.string.article_short_title_5)
                screens[5].stringRes.toLong() -> tab.text =
                    getString(R.string.article_short_title_6)
                screens[6].stringRes.toLong() -> tab.text =
                    getString(R.string.article_short_title_7)
                screens[7].stringRes.toLong() -> tab.text =
                    getString(R.string.article_short_title_8)
            }
        }.attach()
    }

    private fun removeBadge() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.getTabAt(position)?.removeBadge()
            }
        })
    }

    override fun onGenerateBadgeButtonClick() {
        binding.tabLayout.getTabAt(Random.nextInt(screens.size))?.orCreateBadge?.apply {
            badgeGravity = BadgeDrawable.TOP_END
            if (number == 0) number = 1 else number++
        }
    }

    companion object {
        private const val KEY_BOOLEAN_ARRAY = "key_boolean_array"
        private const val KEY_FILTERED_LIST = "key_filtered_list"
    }
}

