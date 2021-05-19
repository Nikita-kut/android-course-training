package com.nikita.kut.android.a18_permissionsanddate.model.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.nikita.kut.android.a18_permissionsanddate.R
import com.nikita.kut.android.a18_permissionsanddate.model.LocationData
import com.nikita.kut.android.a18_permissionsanddate.model.adapters.diffutils.DatasetDiffUtilCallback
import com.nikita.kut.android.a18_permissionsanddate.model.adapters.viewholders.DatasetViewHolder
import com.nikita.kut.android.a18_permissionsanddate.utils.inflate

class DatasetAdapter(private val onItemClicked: (position: Int) -> Unit) :
    ListAdapter<LocationData, DatasetViewHolder>(DatasetDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatasetViewHolder {
        return DatasetViewHolder(parent.inflate(R.layout.item_locations), onItemClicked)
    }

    override fun onBindViewHolder(holder: DatasetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}