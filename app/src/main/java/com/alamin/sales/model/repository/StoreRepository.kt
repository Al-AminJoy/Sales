package com.alamin.sales.model.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.alamin.sales.model.data.Attendance
import com.alamin.sales.model.data.AttendanceResponse
import com.alamin.sales.model.network.ApiInterface
import com.alamin.sales.view.paging.StorePagingSource
import retrofit2.Response
import javax.inject.Inject

private const val TAG = "StoreRepository"

class StoreRepository @Inject constructor(private val apiInterface: ApiInterface) {
    fun getStores() = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { StorePagingSource(apiInterface) }
    ).liveData


    suspend fun createAttendance(attendance: Attendance): String? {
        val response: Response<AttendanceResponse>? = apiInterface.createAttendance(attendance)
        response?.let {
            if (response.isSuccessful) {
                return response.body()?.userMessage
            }
        }
        return null
    }

}