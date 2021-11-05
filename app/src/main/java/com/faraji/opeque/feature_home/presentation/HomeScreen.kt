package com.faraji.opeque.feature_home.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.faraji.opeque.core.presentation.components.CustomTextField
import com.faraji.opeque.core.presentation.util.UiEvent
import com.faraji.opeque.core.presentation.util.asString
import com.faraji.opeque.feature_home.component.ChipItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun HomeScreen(
    scaffoldState: ScaffoldState,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val textFieldState = viewModel.textFieldState.value
    val context = LocalContext.current
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CustomTextField(
            text = textFieldState.text,
            onValueChanged = {
                viewModel.onEvent(HomeScreenEvent.EnteredQuery(it))
            },
            leadingIcon = Icons.Default.Search,
            onSearchAction = {
                keyboardController?.hide()
                viewModel.onEvent(HomeScreenEvent.SearchedForFood(textFieldState.text))
            }
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(CenterHorizontally)
        ) {
            viewModel.tabs.forEachIndexed { index, tabItem ->
                ChipItem(
                    isSelected = pagerState.currentPage == index,
                    title = tabItem.title,
                    icon = tabItem.icon
                ) {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        HorizontalPager(
            count = viewModel.tabs.size,
            state = pagerState
        ) { page ->
            viewModel.tabs[page].screen()
        }
    }
}