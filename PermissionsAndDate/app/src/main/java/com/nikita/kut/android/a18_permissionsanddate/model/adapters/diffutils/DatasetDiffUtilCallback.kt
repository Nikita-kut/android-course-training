package com.nikita.kut.android.a18_permissionsanddate.model.adapters.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.nikita.kut.android.a18_permissionsanddate.model.LocationData

class DatasetDiffUtilCallback : DiffUtil.ItemCallback<LocationData>() {
    override fun areItemsTheSame(oldItem: LocationData, newItem: LocationData): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: LocationData, newItem: LocationData): Boolean =
        oldItem == newItem
}