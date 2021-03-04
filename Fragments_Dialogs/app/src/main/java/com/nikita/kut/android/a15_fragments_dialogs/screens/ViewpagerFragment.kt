package com.nikita.kut.android.a15_fragments_dialogs.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nikita.kut.android.a15_fragments_dialogs.R
import com.nikita.kut.android.a15_fragments_dialogs.model.ArticleScreen
import com.nikita.kut.android.a15_fragments_dialogs.model.ArticleTag
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import java.lang.RuntimeException
import kotlin.math.abs
import kotlin.math.log
import kotlin.random.Random

class ViewpagerFragment : Fragment(R.layout.fragment_viewpager), ArticleFragment.ClickListener {

    private val tags: List<ArticleTag> =
        listOf(ArticleTag.NEWS, ArticleTag.POLITICS, ArticleTag.TECHNOLOGY)

    private val screens: List<ArticleScreen> = listOf(
        (ArticleScreen(R.drawable.ic_article_drawable_1, R.string.article_string_1, tags)),
        (ArticleScreen(R.drawable.ic_article_drawable_2, R.string.article_string_2, tags)),
        (ArticleScreen(R.drawable.ic_article_drawable_3, R.string.article_string_3, tags)),
        (ArticleScreen(R.drawable.ic_article_drawable_4, R.string.article_string_4, tags)),
        (ArticleScreen(R.drawable.ic_article_drawable_5, R.string.article_string_5, tags)),
        (ArticleScreen(R.drawable.ic_article_drawable_6, R.string.article_string_6, tags)),
        (ArticleScreen(R.drawable.ic_article_drawable_7, R.string.article_string_7, tags)),
        (ArticleScreen(R.drawable.ic_article_drawable_8, R.string.article_string_8, tags))
    )
    private val viewPager: ViewPager2 by lazy { requireView().findViewById(R.id.view_pager) }
    private val tabLayout: TabLayout by lazy { requireView().findViewById(R.id.tab_layout) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = ArticleAdapter(activity ?: throw RuntimeException("adapter"), screens)
        viewPager.adapter = adapter

        setDotsIndicator(viewPager)

        setPageAnimation(viewPager)

        setTabLayout(viewPager)
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
                0 -> tab.text = "Circle"
                1 -> tab.text = "Apple"
                2 -> tab.text = "Dino"
                3 -> tab.text = "Snowman"
                4 -> tab.text = "Camera"
                5 -> tab.text = "Icecream"
                6 -> tab.text = "Pickup"
                7 -> tab.text = "Pumpkin"
            }
        }.attach()
    }

    override fun onGenerateBadgeButtonClick() {
        tabLayout.getTabAt(Random.nextInt(screens.size))?.orCreateBadge?.apply {
            badgeGravity = BadgeDrawable.TOP_END
            if (this.number == 0) {
                this.number = 1
            } else {
                this.number++
            }
        }
    }
}
