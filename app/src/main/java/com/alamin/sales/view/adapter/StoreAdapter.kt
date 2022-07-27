package com.alamin.sales.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alamin.sales.R
import com.alamin.sales.databinding.RowStoreBinding
import com.alamin.sales.model.data.Store
import javax.inject.Inject
import kotlin.math.log

private const val TAG = "StoreAdapter"

class StoreAdapter @Inject constructor() :
    PagingDataAdapter<Store, StoreAdapter.StoreViewHolder>(COMPARATOR) {

    private lateinit var listOnClickListener: ListOnClickListener

    inner class StoreViewHolder(val binding: RowStoreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(store: Store) {
            binding.store = store
            binding.onClick = listOnClickListener
        }
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.binding(item)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StoreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = RowStoreBinding.inflate(inflater, parent, false)
        return StoreViewHolder(view)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Store>() {
            override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun setOnClick(listOnClickListener: ListOnClickListener) {
        this.listOnClickListener = listOnClickListener
    }
}