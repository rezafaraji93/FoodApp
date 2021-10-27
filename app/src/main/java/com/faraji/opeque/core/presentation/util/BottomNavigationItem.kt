package com.faraji.opeque.core.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.faraji.opeque.core.util.Screen

sealed class BottomNavigationItem(var route: String, var icon: ImageVector, var title: String) {
    object Home : BottomNavigationItem(Screen.HomeScreen.route, Icons.Default.Home, "Home")
    object Perks : BottomNavigationItem(Screen.PerksScreen.route, Icons.Default.Star, "Perks")
    object Orders :
        BottomNavigationItem(Screen.OrdersScreen.route, Icons.Default.ShoppingCart, "Orders")

    object Account :
        BottomNavigationItem(Screen.AccountScreen.route, Icons.Default.Person, "Account")
}
