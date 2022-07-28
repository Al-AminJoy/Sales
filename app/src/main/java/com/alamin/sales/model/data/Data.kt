package com.alamin.sales.model.data


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("request_id")
    val requestId: String,
    @SerializedName("uid")
    val uid: String,
    @SerializedName("updated_at")
    val updatedAt: String
)