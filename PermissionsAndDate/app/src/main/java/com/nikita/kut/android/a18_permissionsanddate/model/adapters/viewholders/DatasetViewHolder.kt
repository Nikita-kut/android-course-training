package com.nikita.kut.android.a18_permissionsanddate.model.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nikita.kut.android.a18_permissionsanddate.databinding.ItemLocationsBinding
import com.nikita.kut.android.a18_permissionsanddate.model.LocationData
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class DatasetViewHolder(
    view: View,
    onItemClicked: (position: Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    private val binding = ItemLocationsBinding.bind(view)

    private val formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yy")
        .withZone(ZoneId.systemDefault())

    init {
        view.setOnClickListener { onItemClicked(adapterPosition) }
    }

    fun bind(
        locationData: LocationData
    ) {
        binding.tvTimestamp.text = formatter.format(locationData.timestamp)
        Glide.with(itemView)
            .load(locationData.locationPicture)
            .centerCrop()
            .dontAnimate()
            .into(binding.ivLocationPicture)
        binding.tvLat.text = locationData.lat.toString()
        binding.tvLat.text = locationData.lng.toString()
        binding.tvAccuracy.text = locationData.accuracy.toString()
        binding.tvSpeed.text = locationData.speed.toString()
    }
}