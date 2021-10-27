package com.faraji.opeque.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.faraji.opeque.core.presentation.components.BottomNavigationBar
import com.faraji.opeque.core.presentation.components.Navigation
import com.faraji.opeque.core.presentation.ui.theme.MediumGray
import com.faraji.opeque.core.presentation.ui.theme.OpequeTheme
import com.faraji.opeque.core.presentation.ui.theme.White
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    @ExperimentalPagerApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setSystemBarsColor(White, darkIcons = useDarkIcons)
            }
            OpequeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val scaffoldState = rememberScaffoldState()
                    val navController = rememberNavController()
                    Scaffold(
                        scaffoldState = scaffoldState,
                        bottomBar = { BottomNavigationBar(navController = navController) },
                        snackbarHost = {
                            SnackbarHost(hostState = it) { data ->
                                Snackbar(
                                    snackbarData = data,
                                    contentColor = White
                                )
                            }
                        }
                    ) {
                        Navigation(navController = navController, scaffoldState = scaffoldState)
                    }
                }
            }
        }
    }
}
