package com.faraji.opeque.core.domain.response


import com.google.gson.annotations.SerializedName

data class CuisineType(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("priority")
    val priority: Int
)