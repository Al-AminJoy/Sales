package com.alamin.sales.model.data


import com.google.gson.annotations.SerializedName

data class AttendanceResponse(
    @SerializedName("app_message")
    val appMessage: String,
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: Data,
    @SerializedName("user_message")
    val userMessage: String
)