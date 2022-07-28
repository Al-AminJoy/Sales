package com.alamin.sales.view.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.alamin.sales.R
import com.alamin.sales.SalesApplication
import com.alamin.sales.databinding.FragmentAttendanceBinding
import com.alamin.sales.utils.AppUtils
import com.alamin.sales.utils.OnAttendanceResponse
import com.alamin.sales.view.activity.MainActivity
import com.alamin.sales.view_model.StoreViewModel
import com.alamin.sales.view_model.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


private const val TAG = "AttendanceFragment"

class AttendanceFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: StoreViewModel
    private lateinit var binding: FragmentAttendanceBinding
    private val args by navArgs<AttendanceFragmentArgs>()
    private lateinit var progressDialog: ProgressDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAttendanceBinding.inflate(layoutInflater)
        val component = (requireActivity().applicationContext as SalesApplication).appComponent
        component.injectAttendance(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[StoreViewModel::class.java]

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        progressDialog = ProgressDialog(requireActivity())

        binding.setOnSubmitClick {
            if (!AppUtils.isOnline(requireContext())) {
                Toast.makeText(activity, getString(R.string.no_internet), Toast.LENGTH_SHORT)
                    .show()
                return@setOnSubmitClick
            }
            showProgressDialog();
            viewModel.createAttendance(
                (activity as MainActivity).findLocation(),
                object : OnAttendanceResponse {
                    override fun onSuccess(message: String) {
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                        binding.txtAgentName.text?.clear()
                        binding.txtUserId.text?.clear()
                        progressDialog.dismiss()
                    }

                    override fun onFailed(message: String) {
                        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                        progressDialog.dismiss()
                    }

                })

        }
        return binding.root
    }

    private fun showProgressDialog() {
        progressDialog.setMessage(getString(R.string.attendace_message))
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

}