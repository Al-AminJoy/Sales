package com.alamin.sales.view_model;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.alamin.sales.model.data.Attendance
import com.alamin.sales.model.data.Store
import com.alamin.sales.model.repository.StoreRepository
import com.alamin.sales.utils.OnAttendanceResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject;
import kotlin.random.Random

private const val TAG = "StoreViewModel"

class StoreViewModel @Inject constructor(private val repository: StoreRepository) : ViewModel() {

    val name = MutableLiveData<String>()
    val userId = MutableLiveData<String>()

    fun getStoreList(): LiveData<PagingData<Store>> {
        return repository.getStores().cachedIn(viewModelScope)
    }

    fun createAttendance(array: Array<Double>, onAttendanceResponse: OnAttendanceResponse) {
        val agentName = name.value
        val agentId = userId.value

        if (agentName.equals(null) || agentName?.isEmpty() == true) {
            onAttendanceResponse.onFailed("Name Is Empty")
            return
        } else if (agentId.equals(null) || agentId?.isEmpty() == true) {
            onAttendanceResponse.onFailed("User Id Is Empty")
        } else {
            val requestId = generateAlphaNumeric();
            val attendance = name.value?.let {
                userId.value?.let { uId ->
                    Attendance(
                        array[0].toString(), array[1].toString(),
                        it, uId, requestId
                    )
                }
            }

            viewModelScope.launch(IO) {
                if (attendance != null) {
                    val responseMessage = repository.createAttendance(attendance)
                    withContext(Main) {
                        if (responseMessage != null) {
                            onAttendanceResponse.onSuccess(responseMessage)
                        } else {
                            onAttendanceResponse.onFailed("Failed")
                        }
                    }
                }
            }

        }
    }

    private fun generateAlphaNumeric(): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

        return (1..16)
            .map { Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }

}
