package com.faraji.opeque.core.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.faraji.opeque.core.util.Screen
import com.faraji.opeque.feature_Account.presentation.AccountScreen
import com.faraji.opeque.feature_home.presentation.HomeScreen
import com.faraji.opeque.feature_orders.presentation.OrdersScreen
import com.faraji.opeque.feature_perks.presentation.PerksScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun Navigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(scaffoldState = scaffoldState)
        }
        composable(Screen.PerksScreen.route) {
            PerksScreen()
        }
        composable(Screen.OrdersScreen.route) {
            OrdersScreen()
        }
        composable(Screen.AccountScreen.route) {
            AccountScreen()
        }

    }
}