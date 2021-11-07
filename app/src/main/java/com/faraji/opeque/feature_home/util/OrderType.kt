package com.faraji.opeque.feature_home.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
