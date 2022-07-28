package com.alamin.sales.utils

interface OnAttendanceResponse {
    fun onSuccess(message: String)
    fun onFailed(message: String)
}