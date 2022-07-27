package com.alamin.sales.view_model;

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.alamin.sales.model.data.Store
import com.alamin.sales.model.repository.StoreRepository
import javax.inject.Inject;

class StoreViewModel @Inject constructor(private val repository: StoreRepository) : ViewModel(){

    fun getStoreList(): LiveData<PagingData<Store>> {
        return repository.getStores().cachedIn(viewModelScope)
    }

}
