package com.alamin.sales.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.alamin.sales.R
import com.alamin.sales.databinding.FragmentAttendanceBinding

private const val TAG = "AttendanceFragment"
class AttendanceFragment : Fragment() {

    private lateinit var binding: FragmentAttendanceBinding
    private val args by navArgs<AttendanceFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAttendanceBinding.inflate(layoutInflater)
        Log.d(TAG, "onCreateView: ${args.store}")
        return binding.root
    }
}