package com.nikita.kut.android.a17_lists_2_home.adapters

import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.nikita.kut.android.a17_lists_2_home.R
import com.nikita.kut.android.a17_lists_2_home.data.WitcherPerson
import com.nikita.kut.android.a17_lists_2_home.databinding.ItemWildHuntBinding
import com.nikita.kut.android.a17_lists_2_home.util.NetworkMonitorUtil
import com.nikita.kut.android.a17_lists_2_home.util.inflate

class WildhuntAdapterDelegate(
    private val onCardClicked: (position: Int) -> Unit
) : AbsListItemAdapterDelegate<WitcherPerson.WildHunt, WitcherPerson, WildhuntAdapterDelegate.WildHuntHolder>() {

    override fun isForViewType(
        item: WitcherPerson,
        items: MutableList<WitcherPerson>,
        position: Int
    ): Boolean = item is WitcherPerson.WildHunt

    override fun onCreateViewHolder(parent: ViewGroup): WildHuntHolder =
        WildHuntHolder(parent.inflate(R.layout.item_wild_hunt), onCardClicked)

    override fun onBindViewHolder(
        item: WitcherPerson.WildHunt,
        holder: WildHuntHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class WildHuntHolder(
        view: View,
        onCardClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemWildHuntBinding.bind(view)

        private val context = view.context

        init {
            view.setOnClickListener { onCardClicked(adapterPosition) }
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(
            wildHuntPerson: WitcherPerson.WildHunt
        ) {
            Glide.with(itemView).apply {
                if (NetworkMonitorUtil().isOnline(context)) {
                    load(wildHuntPerson.avatarLink)
                        .placeholder(R.drawable.ic_portrait)
                        .dontAnimate()
                        .into(binding.ivWildHuntAvatar)
                } else {
                    load("")
                        .placeholder(R.drawable.ic_no_internet)
                        .dontAnimate()
                        .into(binding.ivWildHuntAvatar)
                }
            }
            binding.tvWildHuntName.text = wildHuntPerson.name
            binding.tvWildHuntColor.text = wildHuntPerson.color
        }
    }
}