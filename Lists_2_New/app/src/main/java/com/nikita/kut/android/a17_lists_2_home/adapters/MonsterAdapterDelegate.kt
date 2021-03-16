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
import com.nikita.kut.android.a17_lists_2_home.databinding.ItemMonsterBinding
import com.nikita.kut.android.a17_lists_2_home.util.NetworkMonitorUtil
import com.nikita.kut.android.a17_lists_2_home.util.inflate

class MonsterAdapterDelegate(
    private val onCardClicked: (position: Int) -> Unit
) : AbsListItemAdapterDelegate<WitcherPerson.Monsters, WitcherPerson, MonsterAdapterDelegate.MonsterHolder>() {

    override fun isForViewType(
        item: WitcherPerson,
        items: MutableList<WitcherPerson>,
        position: Int
    ): Boolean = item is WitcherPerson.Monsters

    override fun onCreateViewHolder(parent: ViewGroup): MonsterHolder =
        MonsterHolder(parent.inflate(R.layout.item_monster), onCardClicked)

    override fun onBindViewHolder(
        item: WitcherPerson.Monsters,
        holder: MonsterHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class MonsterHolder(
        view: View,
        onCardClicked: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val binding = ItemMonsterBinding.bind(view)

        private val context = view.context

        init {
            view.setOnClickListener { onCardClicked(adapterPosition) }
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(
            monsterPerson: WitcherPerson.Monsters
        ) {
            Glide.with(itemView).apply {
                if (NetworkMonitorUtil().isOnline(context)) {
                    load(monsterPerson.avatarLink)
                        .placeholder(R.drawable.ic_portrait)
                        .dontAnimate()
                        .into(binding.ivMonstersAvatar)
                } else {
                    load("")
                        .placeholder(R.drawable.ic_no_internet)
                        .dontAnimate()
                        .into(binding.ivMonstersAvatar)
                }
            }
            binding.tvMonsterName.text = monsterPerson.name
            binding.tvMonsterKind.text = monsterPerson.kind
            binding.tvMonsterSize.text = monsterPerson.size
        }
    }
}