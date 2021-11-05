package com.faraji.opeque.feature_home.presentation

sealed class HomeScreenEvent {
    data class ClickedOnItem(val title: String) : HomeScreenEvent()
    data class SearchedForFood(val query: String) : HomeScreenEvent()
    data class EnteredQuery(val query: String) : HomeScreenEvent()
    data class OnTabClicked(val index: Int) : HomeScreenEvent()

}