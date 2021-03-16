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
import com.nikita.kut.android.a17_lists_2_home.databinding.ItemWitcherBinding
import com.nikita.kut.android.a17_lists_2_home.util.NetworkMonitorUtil
import com.nikita.kut.android.a17_lists_2_home.util.inflate

class WitcherAdapterDelegate(
    private val onCardClicked: (position: Int) -> Unit
) : AbsListItemAdapterDelegate<WitcherPerson.Witchers, WitcherPerson, WitcherAdapterDelegate.WitcherHolder>() {


    override fun isForViewType(
        item: WitcherPerson,
        items: MutableList<WitcherPerson>,
        position: Int
    ): Boolean = item is WitcherPerson.Witchers

    override fun onCreateViewHolder(parent: ViewGroup): WitcherHolder =
        WitcherHolder(parent.inflate(R.layout.item_witcher), onCardClicked)

    override fun onBindViewHolder(
        item: WitcherPerson.Witchers,
        holder: WitcherHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class WitcherHolder(
        view: View,
        onCardClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemWitcherBinding.bind(view)
        private val context = view.context

        init {
            view.setOnClickListener { onCardClicked(adapterPosition) }
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(
            witcherPerson: WitcherPerson.Witchers
        ) {
            Glide.with(itemView).apply {
                if (NetworkMonitorUtil().isOnline(context)) {
                    load(witcherPerson.avatarLink)
                        .placeholder(R.drawable.ic_portrait)
                        .dontAnimate()
                        .into(binding.ivWitcherAvatar)
                } else {
                    load("")
                        .placeholder(R.drawable.ic_no_internet)
                        .dontAnimate()
                        .into(binding.ivWitcherAvatar)
                }
            }
            binding.tvWitcherName.text = witcherPerson.name
            binding.tvWitcherAge.text = witcherPerson.age.toString()
            binding.tvWitcherGender.text = witcherPerson.gender
            binding.tvWitcherSchool.text = witcherPerson.school
        }
    }

}