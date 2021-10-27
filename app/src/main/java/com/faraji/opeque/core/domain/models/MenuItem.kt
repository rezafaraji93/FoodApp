package com.faraji.opeque.core.domain.models

data class MenuItem(
    val id: String,
    val title: String,
    val imageUrl: String,
    val price: Double,
    val rate: Int,
    val deliveryCost: Int,
    val preparationTime: Int
)
