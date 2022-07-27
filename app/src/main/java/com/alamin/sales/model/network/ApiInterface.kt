package com.alamin.sales.model.network

import com.alamin.sales.model.data.ShopList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/stores")
    suspend fun getStores(@Query(value = "page") page: Int): ShopList

}