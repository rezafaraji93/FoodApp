package com.faraji.opeque.core.domain.response

import com.faraji.opeque.core.domain.models.MenuItem
import com.google.gson.annotations.SerializedName

data class MenuResponseItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("subTitle")
    val subTitle: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("ingredients")
    val ingredients: String,
    @SerializedName("minimumCalorie")
    val minimumCalorie: Int,
    @SerializedName("maximumCalorie")
    val maximumCalorie: Int,
    @SerializedName("preparation")
    val preparation: Int,
    @SerializedName("delivery")
    val delivery: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("gallery")
    val gallery: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("fee")
    val fee: Int,
    @SerializedName("rate")
    val rate: Int,
    @SerializedName("isAcceptingDelivery")
    val isAcceptingDelivery: Boolean,
    @SerializedName("isAcceptingPickup")
    val isAcceptingPickup: Boolean,
    @SerializedName("isFavorite")
    val isFavorite: Boolean,
    @SerializedName("isCatering")
    val isCatering: Boolean,
    @SerializedName("isAvailable")
    val isAvailable: Boolean,
    @SerializedName("cuisineType")
    val cuisineType: CuisineType,
    @SerializedName("mealType")
    val mealType: MealType,
    @SerializedName("menuType")
    val menuType: MenuType,
    @SerializedName("courseType")
    val courseType: CourseType,
    @SerializedName("special")
    val special: Special,
    @SerializedName("rewards")
    val rewards: List<Any>
) {
    fun toMenuItem(): MenuItem {
        return MenuItem(
            id = id,
            title = title,
            imageUrl = image,
            price = price,
            rate = rate,
            deliveryCost = delivery,
            preparationTime = preparation
        )
    }
}