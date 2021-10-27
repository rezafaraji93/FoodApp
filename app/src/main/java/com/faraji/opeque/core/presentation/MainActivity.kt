package com.faraji.opeque.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.faraji.opeque.core.presentation.components.BottomNavigationBar
import com.faraji.opeque.core.presentation.components.Navigation
import com.faraji.opeque.core.presentation.ui.theme.OpequeTheme
import com.faraji.opeque.core.presentation.ui.theme.White
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
