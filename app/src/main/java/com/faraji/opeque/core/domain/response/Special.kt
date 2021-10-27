package com.faraji.opeque.core.domain.response


import com.google.gson.annotations.SerializedName

data class Special(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("transcript")
    val transcript: String,
    @SerializedName("policy")
    val policy: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("voucher")
    val voucher: String,
    @SerializedName("beginTime")
    val beginTime: String,
    @SerializedName("remainingTime")
    val remainingTime: Int,
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("percentage")
    val percentage: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("value")
    val value: Int,
    @SerializedName("quantity")
    val quantity: Int
)