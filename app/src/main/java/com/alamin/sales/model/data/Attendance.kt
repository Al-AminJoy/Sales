package com.alamin.sales.model.data


import com.google.gson.annotations.SerializedName

data class Attendance(
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("uid")
    val uid: String,
    @SerializedName("request_id")
    val requestId: String
)