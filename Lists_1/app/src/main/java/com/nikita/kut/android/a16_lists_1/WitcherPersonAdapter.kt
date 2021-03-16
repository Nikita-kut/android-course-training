package com.nikita.kut.android.a16_lists_1

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikita.kut.android.a16_lists_1.databinding.ItemMonsterBinding
import com.nikita.kut.android.a16_lists_1.databinding.ItemWildHuntBinding
import com.nikita.kut.android.a16_lists_1.databinding.ItemWitcherBinding

class WitcherPersonAdapter(
    private val onCardClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var witcherPersons: ArrayList<WitcherPerson> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val witcherView = inflater.inflate(R.layout.item_witcher, parent, false)
        val wildHuntView = inflater.inflate(R.layout.item_wild_hunt, parent, false)
        val monsterView = inflater.inflate(R.layout.item_monster, parent, false)
        return when (viewType) {
            TYPE_WITCHER -> {
                WitcherHolder(witcherView, onCardClicked)
            }
            TYPE_WILD_HUNT -> {
                WildHuntHolder(wildHuntView, onCardClicked)
            }
            TYPE_MONSTER -> {
                MonsterHolder(monsterView, onCardClicked)
            }
            else -> error("Incorrect view type $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (witcherPersons[position]) {
            is WitcherPerson.Witchers -> TYPE_WITCHER
            is WitcherPerson.WildHunt -> TYPE_WILD_HUNT
            is WitcherPerson.Monsters -> TYPE_MONSTER
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WitcherHolder -> {
                val witcherPerson = witcherPersons[position].let { it as? WitcherPerson.Witchers }
                    ?: error("Witcher on position $position is not witcher")
                holder.bind(witcherPerson)
            }
            is WildHuntHolder -> {
                val wildHuntPerson = witcherPersons[position].let { it as? WitcherPerson.WildHunt }
                    ?: error("Wild hunt on position $position is not wild hunt")
                holder.bind(wildHuntPerson)
            }
            is MonsterHolder -> {
                val monsterPerson = witcherPersons[position].let { it as? WitcherPerson.Monsters }
                    ?: error("Monster on position $position is not Monster")
                holder.bind(monsterPerson)
            }
            else -> error("Incorrect holder $holder")
        }
    }

    override fun getItemCount(): Int = witcherPersons.size

    fun updateWitcherPersons(newWitcherPersons: ArrayList<WitcherPerson>) {
        witcherPersons = newWitcherPersons
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

    companion object {
        private const val TYPE_WITCHER = 1
        private const val TYPE_WILD_HUNT = 2
        private const val TYPE_MONSTER = 3
    }

}
