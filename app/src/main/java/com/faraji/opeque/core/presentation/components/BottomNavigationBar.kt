package com.faraji.opeque.core.presentation.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.faraji.opeque.core.presentation.ui.theme.HintGray
import com.faraji.opeque.core.presentation.ui.theme.IconRed
import com.faraji.opeque.core.presentation.util.BottomNavigationItem

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val bottomNavItems = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Perks,
        BottomNavigationItem.Orders,
        BottomNavigationItem.Account
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = IconRed
    ) {
        val navBacStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBacStackEntry?.destination?.route

        bottomNavItems.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                icon = {
                    Icon(painter = painterResource(id = item.icon), contentDescription = item.title)
                },
                label = {
                    Text(text = item.title)
                },
                selectedContentColor = IconRed,
                unselectedContentColor = HintGray,
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}