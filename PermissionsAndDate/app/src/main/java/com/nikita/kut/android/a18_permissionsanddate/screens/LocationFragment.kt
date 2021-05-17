package com.nikita.kut.android.a18_permissionsanddate.screens

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.nikita.kut.android.a18_permissionsanddate.R
import com.nikita.kut.android.a18_permissionsanddate.databinding.FragmentLocationBinding

class LocationFragment : Fragment() {

    private lateinit var binding: FragmentLocationBinding

    private val isLocationPermissionGranted by lazy {
        ActivityCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkLocationPermission()
        binding.btnAllow.setOnClickListener {
            if (!isLocationPermissionGranted) requestLocationPermission()
        }
    }

    private fun checkLocationPermission() {
        if (!isLocationPermissionGranted) {
            binding.tvLocation.text = "Для отображения списка локаций необходимо разрешение"
        } else {
            openLocationListFragment()
        }
    }

    private fun requestLocationPermission() {
        requestPermissions(
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            openLocationListFragment()
        } else {
            toast(resources.getText(R.string.need_permission_for_next_work).toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun openLocationListFragment() {
        requireFragmentManager().beginTransaction()
            .replace(R.id.fragment_main_container, LocationListFragment()).commit()
    }

    private fun toast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 501
    }
}