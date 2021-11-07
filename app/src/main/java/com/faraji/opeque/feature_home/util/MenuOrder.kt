package com.faraji.opeque.feature_home.util

sealed class MenuOrder(val orderType: OrderType) {
    class Price(orderType: OrderType) : MenuOrder(orderType)
    class DeliveryTime(orderType: OrderType) : MenuOrder(orderType)
    class Rating(orderType: OrderType) : MenuOrder(orderType)

    fun copy(orderType: OrderType): MenuOrder {
        return when(this) {
            is DeliveryTime -> DeliveryTime(orderType)
            is Price -> Price(orderType)
            is Rating -> Rating(orderType)
        }
    }
}
