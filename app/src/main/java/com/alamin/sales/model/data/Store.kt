package com.alamin.sales.model.data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Store(
    @SerializedName("address")
    val address: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
):Parcelable