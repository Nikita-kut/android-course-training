package com.nikita.kut.android.a15_fragments_dialogs.screens

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nikita.kut.android.a15_fragments_dialogs.R
import com.nikita.kut.android.a15_fragments_dialogs.model.ArticleScreen
import com.nikita.kut.android.a15_fragments_dialogs.model.ArticleTag
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlin.math.abs
import kotlin.random.Random

class ViewpagerFragment : Fragment(R.layout.fragment_viewpager), ArticleFragment.ClickListener,
    FilterTagsDialogFragment.ConfirmClickListener {

    private val adapter by lazy { ArticleAdapter(this, screens) }
    private var tagsAllChecked = booleanArrayOf(true, true, true)

    private var screens: List<ArticleScreen> = listOf(
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

    private var filteredList: MutableList<ArticleScreen> = mutableListOf()
    private val viewPager: ViewPager2 by lazy { requireView().findViewById(R.id.view_pager) }
    private val tabLayout: TabLayout by lazy { requireView().findViewById(R.id.tab_layout) }
    private val toolbar: Toolbar by lazy { requireView().findViewById(R.id.toolbar) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewPager.adapter = adapter

        setDotsIndicator(viewPager)

        setPageAnimation(viewPager)

        setTabLayout(viewPager)

        removeBadge()

        toolbar.setOnMenuItemClickListener {
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

    override fun onConfirmClick(selectedItems: ArrayList<String>) {

        filteredList = screens.filter { article ->
            article.tags.map { tag -> tag.name }.intersect(selectedItems.toSet()).isNotEmpty()} as MutableList<ArticleScreen>

//        screens.forEachIndexed { index, article ->
//            val tagsInside: Array<String> = article.tags.map { tag -> tag.name }.toTypedArray()
//            for (currentTag in selectedItems.indices) {
//                if (selectedItems[currentTag] == tagsInside[index]) {
//                    filteredList.add(index, article)
//                }
//            }
//        }
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
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.article_short_title_1)
                1 -> tab.text = getString(R.string.article_short_title_2)
                2 -> tab.text = getString(R.string.article_short_title_3)
                3 -> tab.text = getString(R.string.article_short_title_4)
                4 -> tab.text = getString(R.string.article_short_title_5)
                5 -> tab.text = getString(R.string.article_short_title_6)
                6 -> tab.text = getString(R.string.article_short_title_7)
                7 -> tab.text = getString(R.string.article_short_title_8)
            }
        }.attach()
    }

    private fun removeBadge() {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)?.removeBadge()
            }
        })
    }

    override fun onGenerateBadgeButtonClick() {
        tabLayout.getTabAt(Random.nextInt(screens.size))?.orCreateBadge?.apply {
            badgeGravity = BadgeDrawable.TOP_END
            if (number == 0) number = 1 else number++
        }
    }
}

