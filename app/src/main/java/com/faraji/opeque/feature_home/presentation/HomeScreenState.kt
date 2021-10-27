package com.faraji.opeque.feature_home.presentation

import com.faraji.opeque.core.domain.models.MenuItem

data class HomeScreenState(
    val menuItems: List<MenuItem>? = emptyList(),
    val isLoading: Boolean = true,
)