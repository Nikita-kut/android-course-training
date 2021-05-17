package com.nikita.kut.android.a18_permissionsanddate.model.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.nikita.kut.android.a18_permissionsanddate.R
import com.nikita.kut.android.a18_permissionsanddate.model.Dataset
import com.nikita.kut.android.a18_permissionsanddate.model.adapters.diffutils.DatasetDiffUtilCallback
import com.nikita.kut.android.a18_permissionsanddate.model.adapters.viewholders.DatasetViewHolder
import com.nikita.kut.android.a18_permissionsanddate.utils.inflate

class DatasetAdapter : ListAdapter<Dataset, DatasetViewHolder>(DatasetDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatasetViewHolder {
        return DatasetViewHolder(parent.inflate(R.layout.item_locations))
    }

    override fun onBindViewHolder(holder: DatasetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}