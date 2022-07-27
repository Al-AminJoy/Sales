package com.alamin.sales.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.alamin.sales.model.data.Store
import javax.inject.Inject

class StoreDiffutils  @Inject constructor() :
    DiffUtil.Callback() {
    private lateinit var oldList: List<Store>;
    private lateinit var newList: List<Store>

    fun setList(oldList: List<Store>, newList: List<Store>){
        this.oldList = oldList
        this.newList = newList
    }

    override fun getOldListSize(): Int {
        return oldList.size;
    }

    override fun getNewListSize(): Int {
        return newList.size;
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> false
            oldList[oldItemPosition].name != newList[newItemPosition].name -> false
            else -> true
        }
    }
}