package com.faraji.opeque.core.util

sealed class Screen(val route: String) {
    object HomeScreen : Screen(Constants.HOME_SCREEN)
    object PerksScreen : Screen(Constants.PERKS_SCREEN)
    object OrdersScreen : Screen(Constants.ORDERS_SCREEN)
    object AccountScreen : Screen(Constants.ACCOUNT_SCREEN)
}
