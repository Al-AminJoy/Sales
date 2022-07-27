package com.alamin.sales.view.fragment

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.alamin.sales.R
import com.alamin.sales.databinding.FragmentAttendanceBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

private const val TAG = "AttendanceFragment"

class AttendanceFragment : Fragment() {

    private lateinit var binding: FragmentAttendanceBinding
    private val args by navArgs<AttendanceFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAttendanceBinding.inflate(layoutInflater)

        return binding.root
    }







}