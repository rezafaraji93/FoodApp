package com.faraji.opeque.core.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.faraji.opeque.R
import com.faraji.opeque.core.util.Screen

sealed class BottomNavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : BottomNavigationItem(Screen.HomeScreen.route, R.drawable.home, "Home")
    object Perks : BottomNavigationItem(Screen.PerksScreen.route, R.drawable.diamond, "Perks")
    object Orders :
        BottomNavigationItem(Screen.OrdersScreen.route, R.drawable.shopping_cart, "Orders")

    object Account :
        BottomNavigationItem(Screen.AccountScreen.route, R.drawable.account, "Account")
}
