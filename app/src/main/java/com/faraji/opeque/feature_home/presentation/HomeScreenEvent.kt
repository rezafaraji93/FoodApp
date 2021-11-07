package com.faraji.opeque.feature_home.presentation

import com.faraji.opeque.feature_home.util.MenuOrder

sealed class HomeScreenEvent {
    data class ClickedOnItem(val title: String) : HomeScreenEvent()
    data class SearchedForFood(val query: String) : HomeScreenEvent()
    data class EnteredQuery(val query: String) : HomeScreenEvent()
    data class Sort(val menuOrder: MenuOrder) : HomeScreenEvent()
}