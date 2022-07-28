package com.alamin.sales.model.network

import com.alamin.sales.model.data.Attendance
import com.alamin.sales.model.data.AttendanceResponse
import com.alamin.sales.model.data.ShopList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/stores")
    suspend fun getStores(@Query(value = "page") page: Int): ShopList

    @POST("api/attendance")
    suspend fun createAttendance(@Body attendance: Attendance): Response<AttendanceResponse>

}