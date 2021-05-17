package com.nikita.kut.android.a18_permissionsanddate.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.LocationServices
import com.nikita.kut.android.a18_permissionsanddate.databinding.FragmentLocationListBinding
import com.nikita.kut.android.a18_permissionsanddate.model.Dataset
import com.nikita.kut.android.a18_permissionsanddate.model.adapters.DatasetAdapter
import org.threeten.bp.Instant

class LocationListFragment : Fragment() {

    private lateinit var binding: FragmentLocationListBinding

    private var dataset = arrayListOf<Dataset>()

    private var datasetAdapter: DatasetAdapter? = null

    private lateinit var datasetInstant: Dataset

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
            dataset.add(datasetInstant)

            hideHelpTextview()
            datasetAdapter?.submitList(dataset)
        }
    }

    private fun initDatasetInstant() {
        LocationServices.getFusedLocationProviderClient(requireContext())
            .lastLocation
            .addOnSuccessListener {
                it?.let {
                    datasetInstant = Dataset(
                        timestamp = Instant.now(),
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
            datasetAdapter = DatasetAdapter()
            adapter = datasetAdapter
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
}