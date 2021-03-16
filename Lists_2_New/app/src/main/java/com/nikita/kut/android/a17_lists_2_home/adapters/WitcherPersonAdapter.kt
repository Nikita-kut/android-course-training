package com.nikita.kut.android.a17_lists_2_home.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.nikita.kut.android.a17_lists_2_home.data.WitcherPerson

class WitcherPersonAdapter(
    private val onCardClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val differ = AsyncListDiffer(this, WitcherPersonDiffUtilCallBack())
    private val delegatesManager = AdapterDelegatesManager<List<WitcherPerson>>()

    init {
        delegatesManager.addDelegate(WitcherAdapterDelegate(onCardClicked))
            .addDelegate(WildhuntAdapterDelegate(onCardClicked))
            .addDelegate(MonsterAdapterDelegate(onCardClicked))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        delegatesManager.onCreateViewHolder(parent, viewType)

    override fun getItemViewType(position: Int): Int =
        delegatesManager.getItemViewType(differ.currentList, position)

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        delegatesManager.onBindViewHolder(differ.currentList, position, holder)

    fun updateWitcherPersons(newWitcherPersons: ArrayList<WitcherPerson>) {
        differ.submitList(newWitcherPersons)
    }

    class WitcherPersonDiffUtilCallBack : DiffUtil.ItemCallback<WitcherPerson>() {
        override fun areItemsTheSame(oldItem: WitcherPerson, newItem: WitcherPerson): Boolean {
            return when {
                oldItem is WitcherPerson.Witchers && newItem is WitcherPerson.Witchers -> oldItem.id == newItem.id
                oldItem is WitcherPerson.WildHunt && newItem is WitcherPerson.WildHunt -> oldItem.id == newItem.id
                oldItem is WitcherPerson.Monsters && newItem is WitcherPerson.Monsters -> oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: WitcherPerson, newItem: WitcherPerson): Boolean {
            return oldItem == newItem
        }

    }
}
