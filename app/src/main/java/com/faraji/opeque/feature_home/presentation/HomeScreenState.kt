package com.faraji.opeque.feature_home.presentation

import com.faraji.opeque.core.domain.models.MenuItem
import com.faraji.opeque.feature_home.util.MenuOrder
import com.faraji.opeque.feature_home.util.OrderType

data class HomeScreenState(
    val menuItems: List<MenuItem>? = emptyList(),
    val isLoading: Boolean = true,
    val isSortSectionVisible: Boolean = false,
    val menuOrder: MenuOrder = MenuOrder.Price(OrderType.Ascending)
)