package com.alamin.sales.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alamin.sales.R
import com.alamin.sales.SalesApplication
import com.alamin.sales.databinding.FragmentHomeBinding
import com.alamin.sales.model.data.Store
import com.alamin.sales.view.adapter.ListOnClickListener
import com.alamin.sales.view.adapter.StoreAdapter
import com.alamin.sales.view_model.StoreViewModel
import com.alamin.sales.view_model.ViewModelFactory
import javax.inject.Inject

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var storeAdapter: StoreAdapter

    private lateinit var binding: FragmentHomeBinding
    private lateinit var storeViewModel: StoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        val component = (requireActivity().applicationContext as SalesApplication).appComponent
        component.injectHome(this)

        storeViewModel = ViewModelProvider(this, viewModelFactory)[StoreViewModel::class.java]

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = storeAdapter
        }

        storeViewModel.getStoreList().observe(requireActivity(), Observer {
            with(storeAdapter) {
                submitData(lifecycle, it)
                setOnClick(object : ListOnClickListener {
                    override fun onItemClick(store: Store) {
                        val action =
                            HomeFragmentDirections.actionHomeFragmentToAttendanceFragment(store)
                        findNavController().navigate(action)
                    }
                })
            }
        })

        return binding.root
    }

}