package com.nikita.kut.android.a18_permissionsanddate.model.adapters.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.nikita.kut.android.a18_permissionsanddate.model.Dataset

class DatasetDiffUtilCallback : DiffUtil.ItemCallback<Dataset>() {
    override fun areItemsTheSame(oldItem: Dataset, newItem: Dataset): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Dataset, newItem: Dataset): Boolean =
        oldItem == newItem
}