package com.alamin.sales.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.alamin.sales.model.network.ApiInterface
import com.alamin.sales.view.paging.StorePagingSource
import javax.inject.Inject

class StoreRepository @Inject constructor(private val apiInterface: ApiInterface) {
    fun getStores() = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = {StorePagingSource(apiInterface)}
    ).liveData
}