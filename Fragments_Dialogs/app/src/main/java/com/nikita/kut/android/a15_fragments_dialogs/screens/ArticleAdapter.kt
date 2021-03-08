package com.nikita.kut.android.a15_fragments_dialogs.screens

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nikita.kut.android.a15_fragments_dialogs.model.ArticleScreen

class ArticleAdapter(fragment: Fragment, private var screens: List<ArticleScreen>) :
    FragmentStateAdapter(fragment) {

    fun updateList(filteredList: List<ArticleScreen>) {
        screens = filteredList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = screens.size

    override fun getItemId(position: Int): Long = screens[position].stringRes.toLong()

    override fun containsItem(itemId: Long): Boolean =
        screens.any { article -> article.stringRes.toLong() == itemId }

    override fun createFragment(position: Int): Fragment {
        val articleScreen = screens[position]
        return ArticleFragment.newInstance(
            drawableRes = articleScreen.drawableRes,
            stringRes = articleScreen.stringRes
        )
    }
}