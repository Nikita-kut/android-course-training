package com.nikita.kut.android.a18_permissionsanddate.model.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikita.kut.android.a18_permissionsanddate.databinding.ItemLocationsBinding
import com.nikita.kut.android.a18_permissionsanddate.model.Dataset
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class DatasetViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemLocationsBinding.bind(view)

    private val formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yy")
        .withZone(ZoneId.systemDefault())

    fun bind(dataset: Dataset) {
        binding.tvTimestamp.text = formatter.format(dataset.timestamp)
        Glide.with(itemView)
            .load(dataset.locationPicture)
            .centerCrop()
            .dontAnimate()
            .into(binding.ivLocationPicture)
        binding.tvLat.text = dataset.lat.toString()
        binding.tvLat.text = dataset.lng.toString()
        binding.tvAccuracy.text = dataset.accuracy.toString()
        binding.tvSpeed.text = dataset.speed.toString()

    }
}