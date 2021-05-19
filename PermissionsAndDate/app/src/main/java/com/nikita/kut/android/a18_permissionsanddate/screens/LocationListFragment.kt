package com.nikita.kut.android.a18_permissionsanddate.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.LocationServices
import com.nikita.kut.android.a18_permissionsanddate.databinding.FragmentLocationListBinding
import com.nikita.kut.android.a18_permissionsanddate.model.LocationData
import com.nikita.kut.android.a18_permissionsanddate.model.adapters.DatasetAdapter
import jp.wasabeef.recyclerview.animators.LandingAnimator
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

class LocationListFragment : Fragment() {

    private lateinit var binding: FragmentLocationListBinding

    private var dataset = arrayListOf<LocationData>()

    private var datasetAdapter: DatasetAdapter? = null

    private lateinit var locationDataInstant: LocationData

    private lateinit var timeInstant: Instant

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDatasetInstant()
        initRecyclerView()
        binding.btnGetLocation.setOnClickListener {
            initDatasetInstant()
            dataset = (dataset + locationDataInstant) as ArrayList<LocationData>
            hideHelpTextview()
            datasetAdapter?.submitList(dataset)
        }
    }

    private fun initDatasetInstant() {
        timeInstant = Instant.now()
        LocationServices.getFusedLocationProviderClient(requireContext())
            .lastLocation
            .addOnSuccessListener {
                it?.let {
                    locationDataInstant = LocationData(
                        timestamp = timeInstant,
                        lat = it.latitude,
                        lng = it.longitude,
                        accuracy = it.accuracy,
                        speed = it.speed
                    )
                } ?: toast("Локация отсутствует")
            }
            .addOnFailureListener {
                toast("Локация не получена")
            }
            .addOnCanceledListener {
                toast("Получение локации было прервано")
            }
    }

    private fun initRecyclerView() {
        with(binding.rvLocationList) {
            datasetAdapter = DatasetAdapter { position -> openTimePickerDialog(position) }
            adapter = datasetAdapter
            itemAnimator = LandingAnimator()
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            datasetAdapter?.submitList(dataset)
        }
    }

    private fun hideHelpTextview() {
        if (dataset.size > 0) binding.tvLocationList.visibility = View.GONE
    }

    private fun toast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    fun openTimePickerDialog(position: Int) {
        val currentDateTime = LocalDateTime.now()
        val currentItem = dataset[position]

        DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                TimePickerDialog(
                    requireContext(),
                    { _, hourOfDay, minute ->
                        val newInstant =
                            LocalDateTime.of(
                                year,
                                month + 1,
                                dayOfMonth,
                                hourOfDay,
                                minute
                            ).atZone(
                                ZoneId.systemDefault()
                            ).toInstant()
                        currentItem.timestamp = newInstant
                        datasetAdapter?.notifyItemChanged(position)
                    }, currentDateTime.hour,
                    currentDateTime.minute,
                    true
                )
                    .show()
            },
            currentDateTime.year,
            currentDateTime.month.value - 1,
            currentDateTime.dayOfMonth
        )
            .show()
    }

}