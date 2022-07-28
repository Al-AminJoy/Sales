package com.alamin.sales.model.data


import com.google.gson.annotations.SerializedName

data class ShopList(
    @SerializedName("data")
    val storeList: List<Store>,
    @SerializedName("meta")
    val meta: Meta
)